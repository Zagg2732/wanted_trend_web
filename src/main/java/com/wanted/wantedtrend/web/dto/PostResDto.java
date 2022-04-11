package com.wanted.wantedtrend.web.dto;

// Response Dto for Post.class

import com.wanted.wantedtrend.domain.Post;
import com.wanted.wantedtrend.domain.PostLang;
import com.wanted.wantedtrend.enumerate.Lang;
import com.wanted.wantedtrend.enumerate.LangType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PostResDto {

    private String url;         // wd url
    private String date;        // date
    private String crawlDate;   // 크롤링 날짜
    private String status;      // 지원상태
    private String companyName; // 회사이름
    private String location;    // 지역
    private String mainTask;    // 주요업무
    private String requirement; // 요구사항
    private String prefer;      // 우대사항
    private List<String> mainLang; // 주요업무 언어
    private List<String> reqLang;  // 자격요건 언어
    private List<String> preferLang;  // 우대사항 언어

    public Post toPostEntity() {
        return Post.builder()
                .url(url)
                .date(Date.valueOf(date))
                .crawlDate(Date.valueOf(crawlDate))
                .location(location)
                .mainTask(mainTask)
                .requirement(requirement)
                .prefer(prefer)
                .companyName(companyName)
                .build();
    }

    public PostLang toPostLangEntity(Post post, String lang, LangType type) {



        return PostLang.builder()
                .post(post)
///////////////////////////////////////////// .lang(lang)  <- String -> Lang enum 변환 ///////////////////
                .type(type)
                .build();
    }

}
