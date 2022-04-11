package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.LangType;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class TotalLangCnt {
    private LangType langType;
    private Map<String, Long> statistics = new LinkedHashMap<>();

    public void put(String lang, long count){
        statistics.put(lang, count);
    }

    public void setLangType(LangType langType) {
        this.langType = langType;
    }
}
