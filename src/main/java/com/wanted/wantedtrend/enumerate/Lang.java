package com.wanted.wantedtrend.enumerate;

import java.util.Arrays;

// 사용하는 프로그래밍 언어
public enum Lang {
    JAVA("java", "#ED8B00"),
    KOTLIN("kotlin", "#A72DEE"),
    REACT("react", "#61DAFB"),
    JAVASCRIPT("javascript", "#F0DB4F"),
    PYTHON("python", "#3571A2"),
    C("c", "#5A67BB"),
    CPLUS("c++", "#6295CB"),
    CSHARP("c#", "#9C72D6"),
    TYPESCRIPT("typescript", "#3174C0"),
    GO("go", "#00ACDC"),
    VBA("vba", "#90257D"),
    RUBY("ruby", "#A21401"),
    SWIFT("swift", "#F62E22"),
    R("r", "#1D61B0"),
    RUST("rust", "#000000"),
    DART("dart", "#0ACBB2"),
    SCALA("scala", "#D6312D"),
    PERL("perl", "#383A58"),
    GROOVY("groovy", "#5D97B6"),
    MATLAB("matlab", "#FECF01"),
    UNITY("unity", "#010101"),
    UNITY3D("unity3d", "#010101"),
    NODEJS("node", "#78D840"),
    VUE("vue", "#41B27F"),
    ANGULAR("angular", "#D6002F"),
    SOLIDITY("solidity", "#636363"),
    GOLANG("golang", "#00ACDC");

    private final String lang;
    private final String langColor;

    Lang(String lang, String color) { this.lang = lang; this.langColor = color;}

    public String getLangName(){ return lang;}

    public String getLangColor(){return langColor;}

    // DB에서 받아온 문자열을 enum으로 convert
    public static Lang ofLangCode(String langCode) throws Exception {
        return Arrays.stream(Lang.values())
                .filter(v -> v.getLangName().equals(langCode.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new Exception(String.format("Lang enum 에 '%s' 는 존재하지 않습니다", langCode)));
    }

}
