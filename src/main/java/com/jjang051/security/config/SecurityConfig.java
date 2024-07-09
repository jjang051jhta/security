package com.jjang051.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //스프링 3.xx
    //람다만 허용
    //Bean

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .requestMatchers("/css/**","/js/**","/images/**","/h2-console/**")
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->
                auth
                    .requestMatchers("/",
                            "/member/login","/member/signin")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
        );
        httpSecurity.formLogin((auth)->auth
                .loginPage("/member/login")  //get
                .loginProcessingUrl("/member/login")  //post
                .usernameParameter("userEmail")    //name=userId
                .passwordParameter("userPw")  //name=password
                .defaultSuccessUrl("/",true) //true를 쓰지 않으면 이전 페이지로 간다.
        );

        return httpSecurity.build();

    }

}
