package com.example.livechat.service.interfaces;

import com.example.livechat.dtoes.RegisterUserDTO;
import com.example.livechat.dtoes.UserDTO;
import java.util.List;

public interface UserService {
    RegisterUserDTO register(RegisterUserDTO userDTO);
    UserDTO login(UserDTO userDTO);

    List<UserDTO> findByUsernameContainingIgnoreCase(String keyword);
}