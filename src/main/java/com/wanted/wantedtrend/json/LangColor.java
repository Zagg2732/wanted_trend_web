package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.Lang;
import lombok.ToString;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

// 차트 표시 언어색
@ToString
public class LangColor {
    Map<String, String> data;

    public LangColor() {
        data = new LinkedHashMap<>();

        Arrays.stream(Lang.values()).forEach(v ->{
            data.put(v.getLangName(), v.getLangColor());
        });
    }
}
