package com.example.springsecuritystudy.account;

import lombok.*;

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

    public void encodePassword() {
        this.password = "{noop}" + this.password;  //이렇게 스프링시큐리티가 원하는 규칙에 맞게 인코딩 타입을 넣어줘야 됨
    }
}
