package com.example.springsecuritystudy.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Test
    @WithAnonymousUser  //이런식으로 어노테이션으로 목 유저를 넣을 수도 있음
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "ksm", roles = "USER")
    @WithUser  //이런식으로 커스텀 어노테이션을 만들어서 사용할 수도 있음
    public void index_user() throws Exception {
        //mockMvc.perform(get("/").with(user("ksm").roles("USER")))  //이런 유저가 이미 로그인을 한 상태다 라는 것을 가정한것임
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "ksm", roles = "USER")
    @WithUser
    public void admin_user() throws Exception {  // /admin페이지에 일반 USER가 접근하려고 했을 때
//        mockMvc.perform(get("/admin").with(user("ksm").roles("USER")))
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "ksm", roles = "ADMIN")
    public void admin_admin() throws Exception {
//        mockMvc.perform(get("/admin").with(user("ksm").roles("ADMIN")))
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        String username = "ksm";
        String password = "123";
        this.createUser(username, password);

        //password는 레포지토리에 저장될 때 암호화 하고 저장하기 때문에 getPassword()해오면 이미 암호화된 패스워드로 요청이 들어가기 때문에 안됨
        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated());
    }

    private Account createUser(String username, String password) {
        //Account account = Account.of("ksm", "123", "USER");
        Account account = Account.builder()
                .username(username)
                .password(password)
                .role("USER")
                .build();
        return accountService.createNew(account);  //어차피 jpa save()하고 나면 save한 객체를 반환해주니 그걸 바로 return
    }

}