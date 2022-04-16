package com.wanted.wantedtrend.scheduled_service;

import com.google.gson.Gson;
import com.wanted.wantedtrend.enumerate.Lang;
import com.wanted.wantedtrend.enumerate.LangType;
import com.wanted.wantedtrend.json.*;
import com.wanted.wantedtrend.repository.PostLangRepository;
import com.wanted.wantedtrend.repository.PostRepository;
import com.wanted.wantedtrend.utils.ExcelReader;
import com.wanted.wantedtrend.utils.ValidationChecker;
import com.wanted.wantedtrend.web.dto.PostResDto;
import com.wanted.wantedtrend.web.dto.jpa_dto.CountTypeLangDto;
import com.wanted.wantedtrend.web.dto.jpa_dto.CountTypeLangPerDateDto;
import lombok.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CrawlService {

    // get value from application.properties
    @Value("${path.refresh.wanted_json}")
    private String jsonRefreshPath;

    @Value("${path.logging.wanted_json}")
    private String jsonLoggingPath;

    @Value("${path.load.wanted_excel}")
    private String excelPath;

    @Value("${path.load.python_scripts}")
    private String pythonScripts;

    @Value("${path.load.python_main}")
    private String pythonMain;

    @Value("${path.load.python_crawl_command}")
    private String pythonCommand;


    private final PostRepository postRepository;
    private final PostLangRepository postLangRepository;

    // test code - crawl 호출, 추후 schedular 로 동작 //
    @Scheduled(cron = "0 2 2 * * *")
    public void crawl() throws IOException, ParseException, InterruptedException {

        System.out.println("스케줄링에 의해 crawl() 실행됨");

        // python scripts 경로와 main.py 경로 cmd로 실행 (윈도우 OS기준)
        String pythonCmd = String.format("%s %s %s", pythonScripts, pythonMain, pythonCommand); // python.exe main.py daily

        Process process = null;

        if (System.getProperty("os.name").indexOf("Windows") > -1) {    // 윈도우 환경에서 실행
            String windowsCmd = "cmd /c";
            process = Runtime.getRuntime().exec(windowsCmd + pythonCmd);
        } else {                                                        // 리눅스 환경에서 실행
            String[] linuxCmd = {"/bin/sh","-c", pythonCmd};
            process = Runtime.getRuntime().exec(linuxCmd);
        }



        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line = null;
        StringBuffer sb = new StringBuffer();

        sb.append(pythonCmd);

        System.out.println("python crwaling 진행중");

        // python crawl 종료
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // excel 저장까지 좀 기다리기
        System.out.println("python crwaling 종료. file생성 대기");
        Thread.sleep(60000);

        // java로 excel 읽어와서 DB에
        System.out.println("excel file read");
        ExcelReader excelReader = new ExcelReader();

        String today = getPastDate(0).replace("-", "");

        String filename = excelReader.getFilename(today); // format : 20220407

        List<PostResDto> dtoList = excelReader.readExcel(excelPath,filename);
        if (dtoList == null){
            System.out.println("excel file not found error : 주말, 공휴일 등으로 IT 공고가 0건이거나, python error 입니다.");
            return;
        }

        // DB 저장 (JPA)
        saveDB(dtoList);
        System.out.println("database 저장 완료");

        // DB 분석 후 JSON 파일 저장
        databaseAnalyseToJson();
        System.out.println("json 저장 완료");
    }

    // PostResDto List 정보를 DB에 저장
    public void saveDB(List<PostResDto> dtoList) {
        dtoList.forEach(postResDto -> {
            Long postId = postRepository.save(postResDto.toPostEntity()).getId();
            postRepository.findById(postId).ifPresent(post -> {
                postResDto.getMainLang().forEach(lang -> {  // ValidationChecker로 언어가 Nullable이라면 넣지않음
                    if(!ValidationChecker.isNull(lang)){
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, LangType.MAIN));
                    }
                });
                postResDto.getPreferLang().forEach(lang -> {
                    if(!ValidationChecker.isNull(lang)) {
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, LangType.PREFER));
                    }
                });
                postResDto.getReqLang().forEach(lang -> {
                    if(!ValidationChecker.isNull(lang)) {
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, LangType.REQUIREMENT));
                    }
                });
            });
        });
    }

    // DB 분석
    public void databaseAnalyseToJson() throws IOException, ParseException {

        Gson gson = new Gson();

        String title = "메인의 JSON 입니다";

        String today = getPastDate(1);

        // 새로운 공고 업데이트 개수 및 전일대비 증가수
        int updatedCnt = postRepository.countByDate(getPastDate(1));
        if(updatedCnt == 0) { return;}  // 주말, 공휴일같은경우 공고가 없을수있음. 갱신 x
        int comparedCnt = updatedCnt - postRepository.countByDate(getPastDate(2));

        // 메인의 pie chart data set
        TotalLangCnt totalLangCnt = getTotalLangCnts(today);

        // < 타입 , < 언어 , < 날짜, 개수 > > > map
        Map<LangType, Map<String, Map<String, Integer>>> top3LangTrendMap = new LinkedHashMap<>();

        // 사이드 chart data set (날짜별 data)
        totalLangCnt.getData().keySet().forEach(obj -> {

            LangType langType = obj;  // main, requirement, prefer

            top3LangTrendMap.put(langType, createTop3Map(langType, totalLangCnt));
        });

        // 타입별 top3 lang data for side
        Top3LangTrend top3LangTrend = new Top3LangTrend(top3LangTrendMap);


        // 타입별 top lang chart data set
        TopLangInfoJson topLangInfoJson = getTopLangInfo(totalLangCnt);


        // 저장할 json format class
        WantedMainJsonFormat json;

        json = WantedMainJsonFormat.builder()
                                    .date(today)
                                    .title(title)
                                    .updatedCnt(updatedCnt)
                                    .comparedCnt(comparedCnt)
                                    .totalLangCnt(totalLangCnt)
                                    .topLangInfo(topLangInfoJson)
                                    .top3LangTrend(top3LangTrend)
                                    .langColor(new LangColor())
                                    .build();
        // filename (ex 20220413.json)
        String filename = (getPastDate(0) + ".json").replace("-", "");

        // create a writer & path
        Writer refreshJson = Files.newBufferedWriter(Paths.get(jsonRefreshPath + "chart_data.json")); // @\frontend\src\assets\jsons\chart_data.json 덮어씌워서 갱신
        Writer loggingJson = Files.newBufferedWriter(Paths.get(jsonLoggingPath + filename));   // @\json_logs 에 20220401.json 와같이 json 저장

        // convert user object to JSON file
        gson.toJson(json, refreshJson);
        gson.toJson(json, loggingJson);

        // close writer
        refreshJson.close();
        loggingJson.close();
    }

    // 메인의 pie chart data
    public TotalLangCnt getTotalLangCnts(String today) {

        Map<LangType,Map<String, Long>> totalLangCntMap = new LinkedHashMap<>();

        // LangType 별 repository 호출 및 저장
        List<LangType> langTypes = Arrays.asList(LangType.values());
        langTypes.forEach(type -> {
            // <언어, 개수>
            Map<String, Long> langCountMap = new LinkedHashMap<>();
            List<CountTypeLangDto> list = postLangRepository.countPostLangByTypeAndLang(type, today);
            // 공고가없다면 넘어감
            if(list.size() == 0) {return;}
            list.forEach(data -> {
                langCountMap.put(data.getLang().getLangName(), data.getLangCount());
            });

            totalLangCntMap.put(type, langCountMap);
        });
        return new TotalLangCnt(totalLangCntMap);
    }

    // type별 best 언어와 n% of the total 데이터
    public TopLangInfoJson getTopLangInfo(TotalLangCnt totalLangCnt) {

        TopLangInfoJson resultJson = new TopLangInfoJson();

        totalLangCnt.getData().keySet().forEach(langType -> {   // totalLangCount keyset() -> [main, requirement, prefer]

            // values (언어 수) 총합
            Long totalCount = totalLangCnt.getData().get(langType).values().stream().mapToLong(v -> v).sum();

            // values (언어 수) 가 가장 높은 언어를 찾고 TopLangInfo 만들어서 resultJson에 put
            Optional<Map.Entry<String, Long>> maxEntry = totalLangCnt.getData().get(langType).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
            maxEntry.ifPresent(max -> {
                String bestLang = max.getKey();
                Long stack = max.getValue();

                TopLangInfo topLangInfo = TopLangInfo.builder()
                                                     .lang(bestLang)
                                                     .stacks(stack)
                                                     .totalCounts(totalCount)
                                                     .build();

                resultJson.put(langType, topLangInfo);
            });
        });

        return resultJson;
    }

    // 타입별 top3 언어의 최근날짜 데이터 가져오기
    public Map<String, Map<String, Integer>> createTop3Map(LangType langType, TotalLangCnt obj) {
        // <프로그래밍언어, <날짜, 개수>>
        Map<String, Map<String, Integer>> langMap = new LinkedHashMap<>();

        // 공고 개수를 기준으로 내림차순 정렬
        List<Map.Entry<String, Long>> list = obj.getData().get(langType).entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        Collections.reverse(list);

        // 해당 langType의 top3 인기언어 (top3 기준날짜는 오늘)
        for(int i = 0; i < 3; i++) {

            Lang lang = null;  // Lang
            try { lang = Lang.ofLangCode(list.get(i).getKey()); }
            catch (Exception e) { e.printStackTrace(); continue; }

            // Map<날짜, 개수>
            Map<String, Integer> map = new LinkedHashMap<>();

            int dtoCounts = 0;
            int howPast = 0;
            while (dtoCounts < 7 && howPast < 20) {
                try {
                    String date = getPastDate(howPast - 1);
                    howPast++;

                    // countRecentPostLang(타입, 날짜, 언어)
                    CountTypeLangPerDateDto dto = postLangRepository.countRecentPostLang(langType, lang, date);

                    // 해당 날짜에 공고가 없으면 continue
                    if(dto == null) { continue;}
                    else {
                        dtoCounts++;
                        map.put(dto.getDate(), dto.getLangCount());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            langMap.put(lang.getLangName(), map);
        }

        return langMap;
    }

    // 이전날짜값 얻기 (format : "2022-04-01")
    public String getPastDate(int howPast) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date today = new Date();

        String date =  formatter.format(today);

        Date setDate = formatter.parse(date);

        Calendar cal = new GregorianCalendar(Locale.KOREA);

        cal.setTime(setDate);

        cal.add(Calendar.DATE, -howPast); // 이전날짜 -howPast 0 : 오늘, 1 : 어제

        String result = formatter.format(cal.getTime());

        return result;
    }
}
