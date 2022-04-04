package com.wanted.wantedtrend.domain;

import lombok.Builder;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;         // wd url

    private Date date;          // 올린날짜

    private String location;    // 지역

    private String requirement; // 요구사항

    private String prefer;      // 우대사항

    @Column(name = "company_name")
    private String companyName; // 회사이름

    @Column(name = "main_task")
    private String mainTask;    // 주요업무

}
