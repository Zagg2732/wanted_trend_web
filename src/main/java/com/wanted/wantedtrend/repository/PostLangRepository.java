package com.wanted.wantedtrend.repository;

import com.wanted.wantedtrend.domain.PostLang;
import com.wanted.wantedtrend.enumerate.LangType;
import com.wanted.wantedtrend.web.dto.analyse.CountTypeLangDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostLangRepository extends JpaRepository<PostLang, Long> {

    // Post type 별 PostLang count 조회 (type = main / requirement / prefer)
    @Query("SELECT COUNT(pl.lang) AS count, pl.lang AS lang FROM PostLang pl WHERE pl.type = :type GROUP BY pl.lang")
    List<CountTypeLangDto> countPostLangByTypeAndLang(LangType type);

}
