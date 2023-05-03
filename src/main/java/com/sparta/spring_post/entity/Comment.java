package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.spring_post.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@DynamicInsert
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    private Post post;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    @JsonManagedReference
    private Users users;

    @Column(name = "comment_like")
    @ColumnDefault("0")
    private int comment_like;

    @JsonBackReference
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentLike> commentLikeList;

    public Comment(Users user, CommentRequestDto commentRequestDto, Post post) {
        this.post = post;
        this.users = user;
        this.content = commentRequestDto.getContent();
    }


    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public void updateLike(boolean likeOrDislike) {
        this.comment_like = likeOrDislike ? this.comment_like + 1 : this.comment_like - 1;
    }

}
