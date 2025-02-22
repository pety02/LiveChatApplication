package com.example.livechat.web;

import com.example.livechat.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String getRegistrationForm(HttpSession httpSession) {
        return "";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        return "";
    }

    @GetMapping("/login")
    public String getLoginForm(HttpSession httpSession) {
        return "";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        return "";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        return "";
    }
}