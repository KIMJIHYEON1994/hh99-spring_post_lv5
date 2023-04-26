package com.sparta.spring_post.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice //프로젝트 전역에서 발생하는 모든 예외를 잡아준다. 모든 예외를 잡은 후에, Exception 종류별로 메소드를 공통 처리할 수 있다.
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*
     * Developer Custom Exception: 직접 정의한 RestApiException 에러 클래스에 대한 예외 처리
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}