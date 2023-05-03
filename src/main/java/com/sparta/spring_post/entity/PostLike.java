package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JoinColumn(name = "user_name", nullable = false)
    private Users user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostLike(Post post, Users user) {
        this.post = post;
        this.user = user;
    }
}
