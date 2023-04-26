package com.sparta.spring_post.controller;

import com.sparta.spring_post.dto.PostRequestDto;
import com.sparta.spring_post.dto.PostResponseDto;
import com.sparta.spring_post.dto.UserResponseDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.security.UserDetailsImpl;
import com.sparta.spring_post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    // PostService 연결
    private final PostService postService;

    // 목록 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // 상세 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 추가
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest) {
        return postService.createPost(postRequestDto, httpServletRequest);
    }

    // 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest) {
        return postService.updatePost(id, postRequestDto, httpServletRequest);
    }

    // 삭제
    @DeleteMapping("/post/{id}")
    public UserResponseDto<Post> deletePost(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return postService.deletePost(id, httpServletRequest);
    }

    // 좋아요
    @PutMapping("/post/like/{id}")
    public UserResponseDto<Post> updateLike(@PathVariable Long id) {
        return postService.updateLike(id);
    }

}
