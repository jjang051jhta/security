package com.jjang051.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
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

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->
                auth
                    .requestMatchers("/",
                            "/member/login","/member/signin")
                    .permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/mypage/**").hasAnyRole("ADMIN","USER","MANAGER")
                    .anyRequest()
                    .authenticated()
        );
        httpSecurity.formLogin((auth)->auth
                .loginPage("/member/login")  //get
                .loginProcessingUrl("/member/login")  //post
                .usernameParameter("userId")    //name=userId
                .passwordParameter("password")  //name=password
                .defaultSuccessUrl("/",true) //true를 쓰지 않으면 이전 페이지로 간다.
                .failureHandler(customFailureHandler)  //로그인 실패시 어떻게 할건지를 니가 만들어서 처리해라
                .permitAll()
        );
        httpSecurity.logout((auth)->auth
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        );

        return httpSecurity.build();

    }

}
