package com.jjang051.security.service;

import com.jjang051.security.dao.MemberDao;
import com.jjang051.security.dto.CustomUserDetails;
import com.jjang051.security.dto.SigninDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SigninDto signinDto = memberDao.login(username);  //아이디만 가지고 검증하면 password는 시큐리티가 알아서 처리해준다.
        if(signinDto!=null) {
            return new CustomUserDetails(signinDto);
        }
        throw new UsernameNotFoundException("일치하는 멤버가 없습니다.");
        //return null;
    }
}
