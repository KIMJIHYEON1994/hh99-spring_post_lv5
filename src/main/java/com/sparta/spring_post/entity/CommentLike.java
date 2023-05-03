package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JoinColumn(name = "user_name", nullable = false)
    private Users user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;


    public CommentLike(Comment comment, Users user) {
        this.comment = comment;
        this.user = user;
    }
}
