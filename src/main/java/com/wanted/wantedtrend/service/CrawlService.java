package com.wanted.wantedtrend.service;

import com.google.gson.Gson;
import com.wanted.wantedtrend.enumerate.LangType;
import com.wanted.wantedtrend.json.TotalLangCnt;
import com.wanted.wantedtrend.json.WantedMainJsonFormat;
import com.wanted.wantedtrend.repository.PostLangRepository;
import com.wanted.wantedtrend.repository.PostRepository;
import com.wanted.wantedtrend.utils.ValidationChecker;
import com.wanted.wantedtrend.web.dto.PostResDto;
import com.wanted.wantedtrend.web.dto.analyse.CountTypeLangDto;
import lombok.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CrawlService {

    private final PostRepository postRepository;
    private final PostLangRepository postLangRepository;

    // test code - crawl 호출, 추후 schedular 로 동작 //
//    @Scheduled(cron = "0 34 3 ? * *")
//    public void crawl() throws IOException, InterruptedException {
//
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
//
//        // excel 저장까지 좀 기다리기
//        Thread.sleep(10000);
//
//        // java로 excel 읽어와서 DB에
//        ExcelReader excelReader = new ExcelReader();
//
//        //String path = excelReader.getPath();
//
//        String tempPath = "C:/Users/Zagg/PycharmProjects/wanted_trend/excel/";
//
//        String filename = excelReader.getFilename("wanted20220407_163724"); // 2022-04-07
//
//        List<PostResDto> dtoList = excelReader.readExcel(tempPath,filename);
//
//        // DB 저장 (JPA)
//        saveDB(dtoList);
//    }

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
    @Scheduled(cron = "0/5 * * * * ?")
    public void databaseAnalyse() throws IOException {

        Gson gson = new Gson();

        WantedMainJsonFormat json = new WantedMainJsonFormat();

        json.setTitle("메인화면의 data json 입니다.");

        // LangType 별 검색 (enum)
        List<LangType> searchType = Arrays.asList(LangType.values());
        List<TotalLangCnt> totalLangCnts = new ArrayList<>();

        searchType.forEach(type -> {

            // 메인의 pie chart data for JSON
            TotalLangCnt totalLangCnt = new TotalLangCnt();

            totalLangCnt.setLangType(type);

            List<CountTypeLangDto> list = postLangRepository.countPostLangByTypeAndLang(type);
            list.forEach(data -> {
                totalLangCnt.put(data.getLang().getLangName(), data.getCount());
            });

            totalLangCnts.add(totalLangCnt);
        });

        json.setTotalLangCntList(totalLangCnts);


        // create a writer
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Zagg\\Desktop\\test\\test.json"));

        // convert user object to JSON file
        gson.toJson(json, writer);

        // close writer
        writer.close();

    }
}
