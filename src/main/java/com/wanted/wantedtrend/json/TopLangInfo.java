package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.LangType;
import lombok.Builder;
import lombok.ToString;

@ToString
public class TopLangInfo {

    private LangType type;      // 타입 (Main/Req/Prefer)
    private String lang;        // 언어
    private Long stacks;        // 해다 언어 개수
    private Long totalCounts;   // 언어 총 개수
    private String share;       // 지분

    @Builder
    public TopLangInfo(LangType type, String lang, Long stacks, Long totalCounts) {
        this.type = type;
        this.lang = lang;
        this.stacks = stacks;
        this.totalCounts = totalCounts;
        this.share = String.format("%.2f",stacks.doubleValue() / totalCounts.doubleValue() * 100);
    }
}
