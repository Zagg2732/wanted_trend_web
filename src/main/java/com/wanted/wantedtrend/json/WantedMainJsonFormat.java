package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.LangType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class WantedMainJsonFormat {

    private String title;
    private int updatedCnt; // 새로운 업데이트 공고 개수
    private List<TotalLangCnt> totalLangCntList; // 메인의 pie chart data (Today 기준 언어별 count data)
    private List<TopLangInfo> topLangInfo;    // LangType별 메인 <가장 인기있는 언어, % of total>
    private Top3LangTrend top3LangTrend;

}
