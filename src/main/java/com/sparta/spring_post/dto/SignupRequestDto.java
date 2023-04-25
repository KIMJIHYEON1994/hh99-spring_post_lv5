package com.sparta.spring_post.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @Size(min = 4, max = 10, message = "아이디의 길이가 4자 이상 10자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]*$", message = "형식에 맞지 않는 아이디 입니다.")
    private String username;

    @Size(min = 8, max = 15, message = "비밀번호의 길이가 8자 이상 15자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&])[a-zA-Z0-9~!@#$%^&]*$", message = "형식에 맞지 않는 비밀번호 입니다.")
    private String password;
    private boolean admin = false;
    private String adminToken = "";

}
