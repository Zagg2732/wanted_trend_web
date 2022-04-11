package com.wanted.wantedtrend.web.dto.analyse;


import com.wanted.wantedtrend.enumerate.Lang;

// 타입 enum ( main / requirement / prefer) 별 프로그래밍 언어 count
public interface CountTypeLangDto {

    Long getCount();
    Lang getLang();

}
