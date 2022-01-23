package com.example.springsecuritystudy.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SampleController {

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message", "루트페이지입니다. 로그인이 안되어 있어요");
            return "index";
        }
        if (principal != null) {
            model.addAttribute("message", "루트페이지입니다. 로그인 되어있어요 : " + principal.getName());
            return "index";
        }

        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info 페이지입니다.");
        return "info";
    }

    //Dashboard 는 로그인 한 사용자만 접근 가능한 핸들러로 가정한다
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Dashboard 페이지입니다. 로그인 되어있어요 :  " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Admin 페이지입니다. 로그인 되어있어요 : " + principal.getName());
        return "admin";
    }

}
