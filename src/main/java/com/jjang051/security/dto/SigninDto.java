package com.jjang051.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninDto {
    private int id;
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String role;

    private String regDate;

    @Builder
    public SigninDto(String userId, String userName, String password, String email, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
