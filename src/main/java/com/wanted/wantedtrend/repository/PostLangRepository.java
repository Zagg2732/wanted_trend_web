package com.wanted.wantedtrend.repository;

import com.wanted.wantedtrend.domain.PostLang;
import com.wanted.wantedtrend.enumerate.Lang;
import com.wanted.wantedtrend.enumerate.LangType;
import com.wanted.wantedtrend.web.dto.jpa_dto.CountTypeLangDto;
import com.wanted.wantedtrend.web.dto.jpa_dto.CountTypeLangPerDateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PostLangRepository extends JpaRepository<PostLang, Long> {

    // LangType 별 PostLang count 조회 (type = main / requirement / prefer)
    @Query("SELECT COUNT(pl.lang) AS count, pl.lang AS lang FROM PostLang pl WHERE pl.type = :type GROUP BY pl.lang")
    List<CountTypeLangDto> countPostLangByTypeAndLang(LangType type);

    // LangType, lang, date 정보
    @Query("SELECT p.date AS date, count(pl.lang) AS count FROM PostLang pl JOIN Post p ON p.id = pl.post.id WHERE pl.type = :langType AND pl.lang = :lang AND p.date = :date GROUP BY pl.lang, p.date")
    CountTypeLangPerDateDto countRecentPostLang(LangType langType, Lang lang, String date);
}
