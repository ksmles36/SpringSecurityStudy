package com.example.springsecuritystudy.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    //회원가입 계정생성   (예제로 회원가입을 이렇게 만들었음. 실제로는 이런식으로 만들면 보안적으로 절대 안된다)
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {
        return accountService.createNew(account);
    }
}
