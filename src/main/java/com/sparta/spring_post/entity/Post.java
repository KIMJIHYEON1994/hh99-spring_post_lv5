package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.spring_post.dto.PostRequestDto;
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
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", nullable = false)
    private Users users;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    @JsonBackReference
    private List<Comment> comments;

    @Column(name = "post_like")
    @ColumnDefault("0")
    private int like;

    public Post(PostRequestDto postRequestDto, Users user) {
        // 입력값 Validation
        if (user.getUsername() == null) {
            throw new IllegalArgumentException("username이 존재하지 않습니다.");
        }

        if (postRequestDto.getTitle() == null || postRequestDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("글의 제목이 없습니다.");
        }

        if (postRequestDto.getContent() == null || postRequestDto.getContent().isEmpty()) {
            throw new IllegalArgumentException("글의 내용이 없습니다.");
        }

        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.users = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void updateLike(boolean likeOrDislike) {
        this.like = likeOrDislike ? this.like + 1 : this.like - 1;
    }

}
