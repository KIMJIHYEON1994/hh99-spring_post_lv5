package com.sparta.spring_post.repository;

import com.sparta.spring_post.entity.Comment;
import com.sparta.spring_post.entity.CommentLike;
import com.sparta.spring_post.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByCommentAndUser(Comment comment, Users user);
}