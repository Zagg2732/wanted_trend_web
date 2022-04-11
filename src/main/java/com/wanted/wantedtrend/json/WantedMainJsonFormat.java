package com.wanted.wantedtrend.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WantedMainJsonFormat {

    private String title;
    private int updatedCnt; // 새로운 업데이트 공고 개수
    private List<TotalLangCnt> totalLangCntList; // 메인의 pie chart data (Today 기준 언어별 count data)
    // 위와 동일한것으로 판단됨 private List<Map<String, Integer>> dataForPieChartMain; // 메인의 pie chart data (Today 기준 data)

    private Map<String, Integer> topReqInfo;    // 자격요건 메인 <가장 인기있는 언어, % of total>
    private Map<String, Integer> topPreferInfo; // 우대사항 메인 <가장 인기있는 언어, % of total>
    private List<Map<String, Integer>> dataForMainLangChart;   // 메인 우측의 주요업무 chart data (최근 일주일)
    private List<Map<String, Integer>> dataForReqLangChart;    // 메인 우측의 자격요건 chart data (최근 일주일)
    private List<Map<String, Integer>> dataForPreferLangChart; // 메인 우측의 우대사항 chart data (최근 일주일)




}
