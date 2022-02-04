package com.example.springsecuritystudy.account;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "ksm", roles = "ADMIN")
public @interface WithUser {  //이런식으로 커스텀 어노테이션을 만들 수 있음
}
