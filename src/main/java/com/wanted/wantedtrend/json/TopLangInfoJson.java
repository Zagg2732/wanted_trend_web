package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.LangType;

import java.util.LinkedHashMap;
import java.util.Map;

// key :  LangType , value : TopLangInfo 를 가지고있는 class
public class TopLangInfoJson {
    Map<LangType, TopLangInfo> data;

    // No-args constructor
    public TopLangInfoJson() {
        this.data = new LinkedHashMap<>();
    }

    public void put(LangType langType, TopLangInfo topLangInfo){
        this.data.put(langType,topLangInfo);
    }
}
