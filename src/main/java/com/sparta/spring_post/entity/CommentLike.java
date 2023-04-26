package com.sparta.spring_post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;


    public CommentLike(Comment comment, Users user) {
        this.comment = comment;
        this.user = user;
    }
}
