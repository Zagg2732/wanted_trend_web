package com.wanted.wantedtrend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final, Not null 자원을 Autowired 해주는 Constructor
@RestController
@RequestMapping("/api/v1/")
public class PostApiController {

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello World!", HttpStatus.OK);
    }

}
