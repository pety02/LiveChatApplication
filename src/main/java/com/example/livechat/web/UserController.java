package com.example.livechat.web;

import com.example.livechat.dtoes.RegisterUserDTO;
import com.example.livechat.dtoes.UserDTO;
import com.example.livechat.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if (bindingResult.hasErrors()) {
            log.error("Error registering user: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registeredUserDTO", userDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registeredUserDTO", bindingResult);
            return "redirect:register";
        }
        try {
            if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                String errors = "Password and ConfirmPassword mismatch.";
                redirectAttributes.addFlashAttribute("errors", errors);

                if (!redirectAttributes.containsAttribute("registeredUserDTO")) {
                    redirectAttributes.addFlashAttribute("registeredUserDTO", userDTO);
                }
                return "redirect:register";
            }
            log.warn(userDTO.getId() + ", " + userDTO.getUsername() + ", " + userDTO.getEmail() + ", " + userDTO.getPassword());
            RegisterUserDTO registeredUser = userService.register(userDTO);

            if (registeredUser == null || registeredUser.equals(new RegisterUserDTO())) {
                String errors = "Invalid user registration data.";
                redirectAttributes.addFlashAttribute("errors", errors);

                if (!redirectAttributes.containsAttribute("registeredUserDTO")) {
                    redirectAttributes.addFlashAttribute("registeredUserDTO", userDTO);
                }
                return "redirect:register";
            }

            if (!redirectAttributes.containsAttribute("registeredUserDTO")) {
                redirectAttributes.addFlashAttribute("registeredUserDTO", userDTO);
            }
            return "redirect:login";
        } catch (Exception e) {
            if (!redirectAttributes.containsAttribute("registeredUserDTO")) {
                redirectAttributes.addFlashAttribute("registeredUserDTO", userDTO);
            }
            return "redirect:register";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        // Ensure session is cleared before logging in
        if (httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            log.error("Error logging user in: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("loggedInUserDTO", userDTO);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "loggedInUserDTO", bindingResult);
            return "redirect:/login";
        }

        try {
            UserDTO loggedInUser = userService.login(userDTO);

            // If login fails, show error message
            if (loggedInUser == null) {
                redirectAttributes.addFlashAttribute("errors", "Invalid user credentials.");
                log.error("Invalid user credentials.");
                return "redirect:/login";
            }

            // Store logged-in user in session
            UserDetails userDetails = new User(loggedInUser.getUsername(), loggedInUser.getPassword(), Collections.emptyList());
            log.info(loggedInUser.getUsername() + ", " + loggedInUser.getPassword());
            httpSession.setAttribute("loggedInUserDTO", userDetails);

            return "redirect:/index"; // Redirect to homepage after login
        } catch (Exception e) {
            log.error("Login error: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errors", "An error occurred during login.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        return "redirect:/login";
    }
}