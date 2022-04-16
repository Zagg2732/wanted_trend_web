package com.wanted.wantedtrend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor // final, Not null 자원을 Autowired 해주는 Constructor
@RestController
@RequestMapping("/api/v1/")
public class PostApiController {

    @Value("${path.refresh.wanted_json}")
    String jsonFilePath;

    // chart_data.json file 가져와서 전송
    @GetMapping("json")
    public ResponseEntity<String> json() throws IOException {

        String jsonFile = jsonFilePath + "chart_data.json";

        byte[] bytes = Files.readAllBytes(Paths.get(jsonFile));

        return new ResponseEntity<>(new String(bytes), HttpStatus.OK);
    }

}
