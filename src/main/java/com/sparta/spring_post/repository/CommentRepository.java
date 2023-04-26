package com.sparta.spring_post.repository;

import com.sparta.spring_post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
