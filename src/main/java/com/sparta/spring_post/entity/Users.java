package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @Column(name = "user_name", nullable = false, unique = true)
    @Size(min = 4, max = 10, message = "아이디의 길이가 4자 이상 10자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]*$", message = "형식에 맞지 않는 아이디 입니다.")
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    @Size(min = 8, max = 15, message = "비밀번호의 길이가 8자 이상 15자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&])[a-zA-Z\\d~!@#$%^&]*$", message = "형식에 맞지 않는 비밀번호 입니다.")
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public Users(String username, String password, RoleType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
