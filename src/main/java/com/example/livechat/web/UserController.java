package com.example.livechat.web;

import com.example.livechat.dto.RegisterUserDTO;
import com.example.livechat.dto.UserDTO;
import com.example.livechat.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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
            RegisterUserDTO registeredUser = userService.register(userDTO);

            if (registeredUser == null) {
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
                        Model model,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        if(httpSession.getAttribute("loggedInUserDTO") != null) {
            httpSession.invalidate();
        }
        if (bindingResult.hasErrors()) {
            log.error("Error logging user in: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("loggedInUser", userDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "loggedInUser", bindingResult);
            return "redirect:login";
        }
        try {
            UserDTO loggedInUser = userService.login(userDTO);
            if (loggedInUser == null) {
                String errors = "Invalid user credentials.";
                redirectAttributes.addAttribute("errors", errors);
                return "redirect:login";
            }

            httpSession.setAttribute("loggedInUser", loggedInUser);

            return "redirect:/home";
        } catch (Exception e) {
            if (!model.containsAttribute("loggedInUser")) {
                model.addAttribute("loggedInUser", userDTO);
            }
            return "redirect:login";
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