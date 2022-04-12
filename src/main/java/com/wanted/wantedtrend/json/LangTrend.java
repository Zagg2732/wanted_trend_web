package com.wanted.wantedtrend.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LangTrend {
    private Map<String, List<Integer>> trendMap;

    public LangTrend(Map<String, List<Integer>> trendMap) {
        this.trendMap = trendMap;
    }
}
