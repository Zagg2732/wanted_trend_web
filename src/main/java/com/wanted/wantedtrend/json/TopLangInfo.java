package com.wanted.wantedtrend.json;

import lombok.Builder;
import lombok.ToString;

@ToString
public class TopLangInfo {

    private String lang;        // 언어
    private Long stacks;        // 해당 언어 개수
    private Long totalCounts;   // 언어 총 개수
    private String share;       // 지분

    @Builder
    public TopLangInfo(String lang, Long stacks, Long totalCounts) {
        this.lang = lang;
        this.stacks = stacks;
        this.totalCounts = totalCounts;
        this.share = String.format("%.2f",stacks.doubleValue() / totalCounts.doubleValue() * 100);
    }
}
