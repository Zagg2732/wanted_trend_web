package com.wanted.wantedtrend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;         // wd url

    private Date date;          // 올린날짜

    private Date crawlDate;     // 크롤링된 날짜

    private String location;    // 지역

    @Column(name = "main_task", length = 5000)
    private String mainTask;    // 주요업무

    @Column(length = 5000)
    private String requirement; // 요구사항

    @Column(length = 5000)
    private String prefer;      // 우대사항

    @Column(name = "company_name")
    private String companyName; // 회사이름
}
