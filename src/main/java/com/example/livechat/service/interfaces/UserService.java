package com.example.livechat.service.interfaces;

import com.example.livechat.dto.RegisterUserDTO;
import com.example.livechat.dto.UserDTO;

public interface UserService {
    RegisterUserDTO register(RegisterUserDTO userDTO);
    UserDTO login(UserDTO userDTO);
}