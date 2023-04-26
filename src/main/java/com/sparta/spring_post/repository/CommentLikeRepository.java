package com.sparta.spring_post.repository;

import com.sparta.spring_post.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    @Transactional
    @Modifying
    @Query("delete from CommentLike c")
    void deleteFirstBy();


    void deleteByCommentId(Long commentId);
}