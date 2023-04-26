package com.sparta.spring_post.service;

import com.sparta.spring_post.dto.CommentRequestDto;
import com.sparta.spring_post.dto.UserResponseDto;
import com.sparta.spring_post.entity.Comment;
import com.sparta.spring_post.entity.CommentLike;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.entity.Users;
import com.sparta.spring_post.exception.CustomException;
import com.sparta.spring_post.jwt.JwtUtil;
import com.sparta.spring_post.repository.CommentLikeRepository;
import com.sparta.spring_post.repository.CommentRepository;
import com.sparta.spring_post.repository.PostRepository;
import com.sparta.spring_post.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.sparta.spring_post.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentLikeRepository commentLikeRepository;

    // 댓글 등록
    @Transactional
    public UserResponseDto<Comment> addComment(CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        Users user = checkJwtToken(httpServletRequest);

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );


        Comment comment = new Comment(user, commentRequestDto, post);
        commentRepository.save(comment);
        return UserResponseDto.setSuccess("댓글이 등록되었습니다.");
    }

    // 댓글 수정
    @Transactional
    public UserResponseDto<Comment> updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        Users user = checkJwtToken(httpServletRequest);

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(COMMENT_NOT_FOUND)
        );

        if (comment.getUsers().getUsername().equals(user.getUsername()) || user.getRole().equals(user.getRole().ADMIN)) {
            comment.update(commentRequestDto);
            return UserResponseDto.setSuccess("댓글이 수정되었습니다.");
        } else {
            throw new CustomException(INVALID_USER);
        }

    }

    // 댓글 삭제
    @Transactional
    public UserResponseDto<Comment> deleteComment(Long id, HttpServletRequest httpServletRequest) {
        Users user = checkJwtToken(httpServletRequest);

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(COMMENT_NOT_FOUND)
        );

        if (comment.getUsers().getUsername().equals(user.getUsername()) || user.getRole().equals(user.getRole().ADMIN)) {
            commentRepository.delete(comment);
            return UserResponseDto.setSuccess("댓글 삭제 성공");
        } else {
            throw new CustomException(INVALID_USER);
        }

    }

    // 댓글 좋아요
    @Transactional
    public UserResponseDto<Comment> likeComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(COMMENT_NOT_FOUND)
        );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new CustomException(INVALID_USER)
        );

        if (commentLikeRepository.findByCommentAndUser(comment, user) == null) {
            commentLikeRepository.save(new CommentLike(comment, user));
            comment.updateLike(true);
            return UserResponseDto.setSuccess("좋아요 성공");
        } else {
            CommentLike commentLike = commentLikeRepository.findByCommentAndUser(comment, user);
            commentLikeRepository.delete(commentLike);
            comment.updateLike(false);
            return UserResponseDto.setSuccess("좋아요 취소");
        }
    }


    // 토큰 체크
    public Users checkJwtToken(HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 접근 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new CustomException(INVALID_TOKEN);
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Users user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new CustomException(USER_NOT_FOUND)
            );
            return user;

        }
        return null;
    }
}
