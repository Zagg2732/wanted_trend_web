package com.wanted.wantedtrend.json;

import com.wanted.wantedtrend.enumerate.Lang;
import com.wanted.wantedtrend.enumerate.LangType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

// sidebar에 들어갈 top3 lang trned graph data
@Getter
@Setter
@AllArgsConstructor
public class Top3LangTrend {
    Map<LangType, Map<String, Map<String, Integer>>> data;
}
