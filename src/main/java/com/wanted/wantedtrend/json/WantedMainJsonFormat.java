package com.wanted.wantedtrend.json;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WantedMainJsonFormat {

    private String title;
    private int updatedCnt; // 새로운 업데이트 공고 개수
    private TotalLangCnt totalLangCnt; // 메인의 pie chart data (Today 기준 언어별 count data)
    private TopLangInfoJson topLangInfo;    // LangType별 메인 <가장 인기있는 언어, % of total>
    private Top3LangTrend top3LangTrend;
    private LangColor langColor;

}
