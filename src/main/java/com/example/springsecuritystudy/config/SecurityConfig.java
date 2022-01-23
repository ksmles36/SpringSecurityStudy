package com.example.springsecuritystudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/", "/info").permitAll()  //root와 /info 요청은 인증없이 접근 가능
                .mvcMatchers("/admin").hasRole("ADMIN")  ///admin 요청은 ADMIN 권한이 있어야만 접근 가능
                .anyRequest().authenticated()  //그 외의 기타등등 요청은 인증해야 접근 가능
                .and()
                .formLogin()  //폼로그인을 사용하겠다
                .and()
                .httpBasic();  //httpBasic을 사용하겠다
    }
}
