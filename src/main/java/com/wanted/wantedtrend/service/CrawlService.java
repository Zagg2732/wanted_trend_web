package com.wanted.wantedtrend.service;

import com.wanted.wantedtrend.repository.PostLangRepository;
import com.wanted.wantedtrend.repository.PostRepository;
import com.wanted.wantedtrend.utils.ExcelReader;
import com.wanted.wantedtrend.utils.ValidationChecker;
import com.wanted.wantedtrend.web.dto.PostResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CrawlService {

    private final PostRepository postRepository;
    private final PostLangRepository postLangRepository;

    // test code - crawl 호출, 추후 schedular 로 동작 //
    @Scheduled(cron = "0 2 16 ? * *")
    public void crawl() throws IOException, InterruptedException {

        // python scripts 경로와 main.py 경로 cmd로 실행 (윈도우 OS기준)
        String cmd = "/Users/Zagg/PycharmProjects/wanted_trend/venv/Scripts/python.exe /Users/Zagg/PycharmProjects/wanted_trend/main.py";

        Process process = Runtime.getRuntime().exec("cmd /c " + cmd);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line = null;
        StringBuffer sb = new StringBuffer();

        sb.append(cmd);

        // python crawl 종료
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // excel 저장까지 좀 기다리기
        Thread.sleep(10000);

        // java로 excel 읽어와서 DB에
        ExcelReader excelReader = new ExcelReader();

        //String path = excelReader.getPath();

        String tempPath = "C:/Users/Zagg/PycharmProjects/wanted_trend/excel/";

        String filename = excelReader.getFilename("wanted20220407_163724"); // 2022-04-07

        List<PostResDto> dtoList = excelReader.readExcel(tempPath,filename);

        dtoList.forEach(postResDto -> {
            Long postId = postRepository.save(postResDto.toPostEntity()).getId();
            postRepository.findById(postId).ifPresent(post -> {
                postResDto.getMainLang().forEach(lang -> {
                    if(!ValidationChecker.isNull(lang)){
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, "main"));
                    }
                });
                postResDto.getPreferLang().forEach(lang -> {
                    if(!ValidationChecker.isNull(lang)) {
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, "prefer"));
                    }
                });
                postResDto.getReqLang().forEach(lang -> {
                    if(!ValidationChecker.isNull(lang)) {
                        postLangRepository.save(postResDto.toPostLangEntity(post, lang, "requirement"));
                    }
                });
            });
        });

    }
}
