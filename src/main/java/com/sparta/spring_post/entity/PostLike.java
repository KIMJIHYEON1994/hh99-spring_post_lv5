package com.sparta.spring_post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostLike(Post post, Users user) {
        this.post = post;
        this.user = user;
    }
}
