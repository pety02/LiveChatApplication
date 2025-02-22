package com.example.livechat.service;

import com.example.livechat.dto.UserDTO;
import com.example.livechat.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO register(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO login(UserDTO login) {
        return null;
    }

    @Override
    public void logout() {

    }
}