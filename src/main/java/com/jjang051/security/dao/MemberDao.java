package com.jjang051.security.dao;

import com.jjang051.security.dto.SigninDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int signin(SigninDto signinDto);

    SigninDto login(String username);
}
