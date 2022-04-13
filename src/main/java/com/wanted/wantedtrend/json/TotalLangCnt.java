package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.LangType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
public class TotalLangCnt {
    private Map<LangType, Map<String, Long>> data;
}
