package com.sparta.spring_post.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST */
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다"),
    INVALID_USER(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다."),
    INVALID_USER_EXISTENCE(HttpStatus.BAD_REQUEST, "중복된 username 입니다."),
    INVALID_USER_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호를 다시 입력해주세요."),
    INVALID_ADMIN_PASSWORD(HttpStatus.BAD_REQUEST, "관리자의 비밀번호를 다시 입력해주세요."),



    /* 401 UNAUTHORIZED */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),


    /* 404 NOT_FOUND */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다"),


    /* 409 CONFLICT */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다");



    private final HttpStatus httpStatus;
    private final String message;
}