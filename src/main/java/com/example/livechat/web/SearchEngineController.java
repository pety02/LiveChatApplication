package com.example.livechat.web;

import com.example.livechat.dtoes.UserDTO;
import com.example.livechat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SearchEngineController {
    private final UserService userService;

    @Autowired
    public SearchEngineController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam String keyword) {
        return userService.findByUsernameContainingIgnoreCase(keyword);
    }
}