package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @GetMapping(value = "/userInfo")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("roles", user.getRoleSet());
        return "userInfo";
    }
}
