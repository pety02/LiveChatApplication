package com.example.livechat.service.interfaces;

import com.example.livechat.dtoes.RegisterUserDTO;
import com.example.livechat.dtoes.UserDTO;

public interface UserService {
    RegisterUserDTO register(RegisterUserDTO userDTO);
    UserDTO login(UserDTO userDTO);
}