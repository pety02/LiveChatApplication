package com.example.livechat.web;

import com.example.livechat.dtoes.RegisterUserDTO;
import com.example.livechat.dtoes.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/index")
    public String showIndex(Model model, HttpSession httpSession, HttpServletRequest request) {
        // Retrieve user from session
        UserDTO loggedInUser = (UserDTO) httpSession.getAttribute("loggedInUserDTO");

        model.addAttribute("httpServletRequest", request);
        if (loggedInUser != null) {
            model.addAttribute("loggedInUserDTO", loggedInUser);
        } else {
            model.addAttribute("loggedInUserDTO", null);
        }

        return "index";  // Return the index.html page
    }
}
