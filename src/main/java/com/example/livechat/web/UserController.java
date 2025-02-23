package com.example.livechat.web;

import com.example.livechat.dto.UserDTO;
import com.example.livechat.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(HttpSession httpSession,
                                      Model model) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(!model.containsAttribute("registeredUserDTO")) {
            model.addAttribute("registerUserDTO", new UserDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registeredUser", userDTO);
            return "redirect:/register";
        }
        UserDTO registeredUserDTO = userService.register(userDTO);
        if(registeredUserDTO == null) {
            redirectAttributes.addFlashAttribute("errors", "Registration failed. Please, try again!");
            redirectAttributes.addFlashAttribute("registeredUser", registeredUserDTO);
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginForm(HttpSession httpSession,
                               Model model) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(!model.containsAttribute("loggedInUserDTO")) {
            model.addAttribute("loggedInUserDTO", new UserDTO());
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "loggedInUser", userDTO);
            return "redirect:/login";
        }
        UserDTO loggedInUser = userService.login(userDTO);
        if(loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errors", "Login failed because of incorrect username or password. Please, try again!");
            redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        return "redirect:/login";
    }
}