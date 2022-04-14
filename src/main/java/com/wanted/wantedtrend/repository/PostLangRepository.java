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

    // LangType 별 PostLang count 조회 (type = main / requirement / prefer) , langCount 기준 내림차순
    @Query("SELECT COUNT(pl.lang) AS langCount, pl.lang AS lang FROM PostLang pl JOIN Post p ON p.id = pl.post.id WHERE pl.type = :type AND p.date = :today GROUP BY pl.lang ORDER BY langCount DESC")
    List<CountTypeLangDto> countPostLangByTypeAndLang(LangType type, String today);

    // LangType, lang, date 정보 , langCount 기준 내림차순
    @Query("SELECT p.date AS date, count(pl.lang) AS langCount FROM PostLang pl JOIN Post p ON p.id = pl.post.id WHERE pl.type = :langType AND pl.lang = :lang AND p.date = :date GROUP BY pl.lang, p.date ORDER BY langCount DESC")
    CountTypeLangPerDateDto countRecentPostLang(LangType langType, Lang lang, String date);
}
