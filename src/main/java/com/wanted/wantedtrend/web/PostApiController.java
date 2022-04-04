package com.wanted.wantedtrend.web;

import com.wanted.wantedtrend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final, Not null 자원을 Autowired 해주는 Constructor, 여기선 PostService를 위함
@RestController
@RequestMapping("/api/v1/")
public class PostApiController {

    private final PostService postService;

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        String userDirPath = System.getProperty("user.dir");
        String test = userDirPath.substring(0, userDirPath.lastIndexOf("/") + 1);

        String checkPath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("/") + 1);

        System.out.println("this.getClass().getPackage().getName() == "+this.getClass().getPackage().getName());
        System.out.println("this.getClass().getProtectionDomain() == "+this.getClass().getProtectionDomain());

        return new ResponseEntity<String>("hello" + System.getProperty("os.name") + " - " + System.getProperty("user.dir") + " - " + test + " - " + checkPath, HttpStatus.OK);
    }

}
