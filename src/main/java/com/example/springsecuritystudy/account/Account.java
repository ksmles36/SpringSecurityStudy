package com.example.springsecuritystudy.account;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @Builder  //빌더패턴 방식
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        //this.password = "{noop}" + this.password;  //이렇게 스프링시큐리티가 원하는 규칙에 맞게 인코딩 타입을 넣어줘야 됨
        this.password = passwordEncoder.encode(this.password);
    }

    //정적 팩토리 메소드 방식
    public static Account of(String username, String password, String role) {
        return new Account(username, password, role);
    }
}
