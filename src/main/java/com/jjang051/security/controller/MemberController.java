package com.jjang051.security.controller;

import com.jjang051.security.dto.Role;
import com.jjang051.security.dto.SigninDto;
import com.jjang051.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {


    private final MemberService memberService;


    @GetMapping("/login")
    public String login() {
        return "member/login";
    }



    @GetMapping("/signin")
    public String signin() {
        return "member/signin";
    }

    @PostMapping("/signin")
    public String signinProcess(@ModelAttribute SigninDto signinDto) {
        //db에 넣어주면 됨...
        //log.info(bCryptPasswordEncoder.encode("1234"));
        //log.info("role==={}",Role.ADMIN.getRole());
        //log.info("role==={}",Role.ADMIN.getGrade());
        //암호화
        int result = memberService.signin(signinDto);
        return "redirect:/member/login";
    }



    @GetMapping("/mypage")
    public String mypage() {
        return "member/mypage";
    }

}
