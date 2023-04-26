package com.sparta.spring_post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor(staticName = "set")
public class UserResponseDto<D> {
    private String message;
    private int statusCode;

    public static <D> UserResponseDto<D> setSuccess(String message) {
        return UserResponseDto.set(message, HttpStatus.OK.value());
    }
}
