package com.example.springsecuritystudy.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SampleController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring Security");
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    //Dashboard 는 로그인 한 사용자만 접근 가능한 핸들러로 가정한다
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "dashboard");
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("message", "admin");
        return "admin";
    }

}
