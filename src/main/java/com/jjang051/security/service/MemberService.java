package com.jjang051.security.service;

import com.jjang051.security.dao.MemberDao;
import com.jjang051.security.dto.Role;
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
        SigninDto signinDto02 = SigninDto.builder()
                .userName(signinDto.getUserName())
                .email(signinDto.getEmail())
                .userId(signinDto.getUserId())
                .password(bCryptPasswordEncoder.encode(signinDto.getPassword()))
                //.role("ROLE_"+signinDto.getRole())
                .role(Role.USER.getRole())
                .build();
        return memberDao.signin(signinDto02);
    }
    //생성자 주입이 됨...
}
