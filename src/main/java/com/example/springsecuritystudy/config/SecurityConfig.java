package com.example.springsecuritystudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/", "/info", "/account/**").permitAll()  //root와 /info 요청은 인증없이 접근 가능
                .mvcMatchers("/admin").hasRole("ADMIN")  ///admin 요청은 ADMIN 권한이 있어야만 접근 가능
                .anyRequest().authenticated()  //그 외의 기타등등 요청은 인증해야 접근 가능
                .and()
                .formLogin()  //폼로그인을 사용하겠다
                .and()
                .httpBasic();  //httpBasic을 사용하겠다
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //인메모리 방식으로 스프링시큐리티에 유저 정보를 등록함(DB에 넣은건 아님)  username/pw/roles
        auth.inMemoryAuthentication()
                .withUser("ksm").password("{noop}123").roles("USER").and()
                .withUser("admin").password("{noop}!@#").roles("ADMIN");
    }*/
}
