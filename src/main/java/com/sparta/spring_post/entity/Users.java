package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
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
