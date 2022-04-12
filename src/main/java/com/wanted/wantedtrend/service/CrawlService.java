package com.wanted.wantedtrend.service;

import com.google.gson.Gson;
import com.wanted.wantedtrend.enumerate.Lang;
import com.wanted.wantedtrend.enumerate.LangType;
import com.wanted.wantedtrend.json.Top3LangTrend;
import com.wanted.wantedtrend.json.TopLangInfo;
import com.wanted.wantedtrend.json.TotalLangCnt;
import com.wanted.wantedtrend.json.WantedMainJsonFormat;
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

    private final PostRepository postRepository;
    private final PostLangRepository postLangRepository;

    // test code - crawl 호출, 추후 schedular 로 동작 //
//    @Scheduled(cron = "0 28 14 * * *")
    @Scheduled(cron = "0/20 * * * * *")
    public void crawl() throws IOException {

//        // python scripts 경로와 main.py 경로 cmd로 실행 (윈도우 OS기준)
//        String cmd = "/Users/Zagg/PycharmProjects/wanted_trend/venv/Scripts/python.exe /Users/Zagg/PycharmProjects/wanted_trend/main.py begin";
//
//        Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
//
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(process.getInputStream()));
//
//        String line = null;
//        StringBuffer sb = new StringBuffer();
//
//        sb.append(cmd);
//
//        // python crawl 종료
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }

//        // excel 저장까지 좀 기다리기
//        Thread.sleep(10000);
//
        // java로 excel 읽어와서 DB에
        ExcelReader excelReader = new ExcelReader();

        // String path = excelReader.getPath();

        String tempPath = "C:/Users/Zagg/PycharmProjects/wanted_trend/excel/";

        String filename = excelReader.getFilename("wanted20220411_194705"); // 2022-04-07

        List<PostResDto> dtoList = excelReader.readExcel(tempPath,filename);


        // DB 저장 (JPA)
//        saveDB(dtoList);

        // DB 분석 후 JSON 파일 저장
        databaseAnalyse(dtoList.size());
    }

    // PostResDto List 정보를 DB에 저장
    public void saveDB(List<PostResDto> dtoList) {
        dtoList.forEach(postResDto -> {
            Long postId = postRepository.save(postResDto.toPostEntity()).getId();
            postRepository.findById(postId).ifPresent(post -> {
                postResDto.getMainLang().forEach(lang -> {
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
//    @Scheduled(cron = "0/10 0 0 * * *")
    public void databaseAnalyse(int updatedCnt) throws IOException {

        Gson gson = new Gson();

        String title = "메인의 JSON 입니다";

        // 메인의 pie chart data set
        List<TotalLangCnt> totalLangCnts = getTotalLangCnts();

        // < 타입 , < 언어 , < 날짜, 개수 > > > map
        Map<LangType, Map<Lang, Map<String, Integer>>> top3LangTrendMap = new LinkedHashMap<>();

        // 사이드 chart data set (날짜별 data)
        totalLangCnts.forEach(obj -> {

            LangType langType = obj.getLangType();  // main, requirement, prefer

            top3LangTrendMap.put(langType, createTop3Map(obj));
        });

        // 타입별 top3 lang data for side
        Top3LangTrend top3LangTrend = new Top3LangTrend(top3LangTrendMap);


        // 타입별 top lang chart data set
        List<TopLangInfo> topLangInfo = getTopLangInfo(totalLangCnts);


        // 저장할 json format class
        WantedMainJsonFormat json;

        json = WantedMainJsonFormat.builder()
                                    .title(title)
                                    .updatedCnt(updatedCnt)
                                    .totalLangCntList(totalLangCnts)
                                    .topLangInfo(topLangInfo)
                                    .top3LangTrend(top3LangTrend)
                                    .build();

        // create a writer
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Zagg\\Desktop\\test\\test.json"));

        // convert user object to JSON file
        gson.toJson(json, writer);

        // close writer
        writer.close();
    }

    // 메인의 pie chart data
    public List<TotalLangCnt> getTotalLangCnts() {
        // LangType 별 repository 호출 및 저장
        List<TotalLangCnt> totalLangCnts = new ArrayList<>();
        List<LangType> langTypes = Arrays.asList(LangType.values());
        langTypes.forEach(type -> {

            // 메인의 pie chart data for JSON
            TotalLangCnt totalLangCnt = new TotalLangCnt();

            totalLangCnt.setLangType(type);

            List<CountTypeLangDto> list = postLangRepository.countPostLangByTypeAndLang(type);
            list.forEach(data -> {
                totalLangCnt.put(data.getLang().getLangName(), data.getCount());
            });

            totalLangCnts.add(totalLangCnt);
        });

        return totalLangCnts;
    }

    // type별 best 언어와 n% of the total 데이터
    public List<TopLangInfo> getTopLangInfo(List<TotalLangCnt> totalLangCnts) {

        // 결과 list
        List<TopLangInfo> result = new ArrayList<>();

        totalLangCnts.forEach(type -> {
            // 언어 총계
            Long totalCount = type.getStatistics().values().stream().mapToLong(v -> v).sum();
            // 가장 많은 공고를 보유한 언어와 개수 필터
            Optional<Map.Entry<String, Long>> maxEntry =
                    type.getStatistics().entrySet()
                                        .stream()
                                        .max(Comparator.comparing(Map.Entry::getValue));
            maxEntry.ifPresent(max -> {
                String best = max.getKey();
                Long stack = max.getValue();

                // List에 넣을 객체 생성
                TopLangInfo topLangInfo = TopLangInfo.builder()
                                                     .type(type.getLangType())
                                                     .lang(best)
                                                     .stacks(stack)
                                                     .totalCounts(totalCount)
                                                     .build();
                result.add(topLangInfo);
            });
        });
        return result;
    }

    // 타입별 top3 언어의 최근날짜 데이터 가져오기
    public Map<Lang, Map<String, Integer>> createTop3Map(TotalLangCnt obj) {
        // <프로그래밍언어, <날짜, 개수>>
        Map<Lang, Map<String, Integer>> langMap = new LinkedHashMap<>();

        // 공고 개수를 기준으로 내림차순 정렬
        List<Map.Entry<String, Long>> list = obj.getStatistics().entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        Collections.reverse(list);

        LangType langType = obj.getLangType();  // main, requirement, prefer

        // 해당 langType의 top3 인기언어 (top3 기준날짜는 오늘)
        for(int i = 0; i < 3; i++) {

            Lang lang = null;  // Lang
            try { lang = Lang.ofLangCode(list.get(i).getKey()); }
            catch (Exception e) { e.printStackTrace(); }

            // Map<날짜, 개수>
            Map<String, Integer> map = new LinkedHashMap<>();

            int dtoCounts = 0;
            int howPast = 0;
            while (dtoCounts < 7 && howPast < 20) {
                try {
                    String date = getPastDate(howPast);
                    howPast++;

                    // countRecentPostLang(타입, 날짜, 언어)
                    CountTypeLangPerDateDto dto = postLangRepository.countRecentPostLang(langType, lang, date);

                    // 해당 날짜에 공고가 없으면 continue
                    if(dto == null) { continue;}
                    else {
                        dtoCounts++;
                        map.put(dto.getDate(), dto.getCount());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            langMap.put(lang, map);
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
