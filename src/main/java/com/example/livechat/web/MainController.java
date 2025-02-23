package com.example.livechat.web;

import com.example.livechat.dto.RegisterUserDTO;
import com.example.livechat.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class MainController {
    @GetMapping("/register")
    public String getRegistrationForm(HttpSession httpSession,
                                      Model model) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(!model.containsAttribute("registeredUserDTO")) {
            model.addAttribute("registeredUserDTO", new RegisterUserDTO());
        }
        return "register";
    }

    @GetMapping("/login")
    public String getLoginForm(HttpSession httpSession,
                               Model model) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if (!model.containsAttribute("loggedInUserDTO")) {
            model.addAttribute("loggedInUserDTO", new UserDTO());
        }
        return "login";
    }

    @GetMapping("/home")
    public String getHomePage(@ModelAttribute UserDTO loggedInUser,
                              HttpSession httpSession,
                              Model model) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(!model.containsAttribute("loggedInUserDTO")) {
            model.addAttribute("loggedIndUserDTO", loggedInUser);
        }
        return "home";
    }
}
