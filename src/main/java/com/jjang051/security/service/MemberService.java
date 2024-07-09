package com.jjang051.security.service;

import com.jjang051.security.dao.MemberDao;
import com.jjang051.security.dto.SigninDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int signin(SigninDto signinDto) {
        SigninDto.builder()
                .userName(signinDto.getUserName())
                .email(signinDto.getEmail())
                .userId(signinDto.getUserId())
                .role("ROLE_"+signinDto.getRole())
                .build();
        return 1;
    }
    //생성자 주입이 됨...
}
