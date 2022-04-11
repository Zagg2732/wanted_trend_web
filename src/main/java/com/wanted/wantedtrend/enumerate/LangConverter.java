package com.wanted.wantedtrend.enumerate;

import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// db에서 불러올때 enum값 convert
// 컨버터 필요 예시) db 값이 java 일때 enum JAVA 와 매칭이 안되는 오류발생

@Converter
public class LangConverter implements AttributeConverter<Lang, String> {

    @Override
    public String convertToDatabaseColumn(Lang attribute) {
        return attribute.getLangName();
    }

    @SneakyThrows
    @Override
    public Lang convertToEntityAttribute(String dbData) {
        return Lang.ofLangCode(dbData);
    }
}
