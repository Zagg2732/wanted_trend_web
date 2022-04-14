package com.wanted.wantedtrend.web.dto.jpa_dto;

// 타입 enum ( main / requirement / prefer) 별 프로그래밍 언어 count
public interface CountTypeLangPerDateDto {

    int getLangCount();
    String getDate();

}
