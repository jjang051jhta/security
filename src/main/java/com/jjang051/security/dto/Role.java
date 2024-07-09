package com.jjang051.security.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN",10),
    MANAGER("ROLE_MANAGER",5),
    USER("ROLE_USER",1);

    private final String role;
    private final int grade;

}
