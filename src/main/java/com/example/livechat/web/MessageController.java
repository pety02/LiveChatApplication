package com.example.livechat.web;

import com.example.livechat.dtoes.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {
    @GetMapping("/user-messages")
    public String getAllMessages(HttpSession httpSession,
                                 UserDTO userDTO) {
        return "";
    }

    @PostMapping("/all-messages/{id}")
    public String sendMessage(@ModelAttribute UserDTO userFromDTO,
                           @ModelAttribute UserDTO userToDTO,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        return "";
    }
}