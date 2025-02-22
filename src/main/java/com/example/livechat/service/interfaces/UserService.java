package com.example.livechat.service.interfaces;

import com.example.livechat.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
}