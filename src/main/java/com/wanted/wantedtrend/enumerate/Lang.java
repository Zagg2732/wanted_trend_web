package com.wanted.wantedtrend.enumerate;

import java.util.Arrays;

// 사용하는 프로그래밍 언어
public enum Lang {
    JAVA("java"),
    KOTLIN("kotlin"),
    REACT("react"),
    JAVASCRIPT("javascript"),
    PYTHON("python"),
    C("c"),
    CPLUS("c++"),
    CSHARP("c#"),
    TYPESCRIPT("typescript"),
    GO("go"),
    VBA("vba"),
    RUBY("ruby"),
    SWIFT("swift"),
    R("r"),
    RUST("rust"),
    DART("dart"),
    SCALA("scala"),
    PERL("perl"),
    GROOVY("groovy"),
    MATLAB("matlab"),
    UNITY("unity"),
    UNITY3D("unity3d"),
    NODEJS("node.js"),
    VUE("vue"),
    ANGULAR("angular"),
    SOLIDITY("solidity"),
    GOLANG("golang");

    Lang(String value) { this.lang = value; }

    private final String lang;

    public String getLangName(){ return lang;}

    // DB에서 받아온 문자열을 enum으로 convert
    public static Lang ofLangCode(String langCode) throws Exception {
        return Arrays.stream(Lang.values())
                .filter(v -> v.getLangName().equals(langCode.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new Exception(String.format("Lang enum 에 '%s' 는 존재하지 않습니다", langCode)));
    }

}
