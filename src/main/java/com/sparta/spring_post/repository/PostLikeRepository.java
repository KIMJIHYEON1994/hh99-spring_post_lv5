package com.sparta.spring_post.repository;

import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.entity.PostLike;
import com.sparta.spring_post.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findByPostAndUser(Post post, Users user);
}
