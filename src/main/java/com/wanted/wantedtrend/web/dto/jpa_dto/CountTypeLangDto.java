package com.wanted.wantedtrend.web.dto.jpa_dto;


import com.wanted.wantedtrend.enumerate.Lang;

// 타입 enum ( main / requirement / prefer) 별 프로그래밍 언어 count
public interface CountTypeLangDto {

    Long getLangCount();
    Lang getLang();

}
