package com.wanted.wantedtrend.repository;

import com.wanted.wantedtrend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByDate(String date);

    int countByDate(String date);
}
