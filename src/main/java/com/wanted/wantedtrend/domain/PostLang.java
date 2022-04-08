package com.wanted.wantedtrend.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "post_lang")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근제어 참조 -> https://cobbybb.tistory.com/14
@AllArgsConstructor
@Builder
public class PostLang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    private String lang;

    private String type;
}
