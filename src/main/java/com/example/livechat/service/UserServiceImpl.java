package com.example.livechat.service;

import com.example.livechat.dto.RegisterUserDTO;
import com.example.livechat.dto.UserDTO;
import com.example.livechat.entities.UserEntity;
import com.example.livechat.mapper.RegisterUserMapper;
import com.example.livechat.mapper.UserMapper;
import com.example.livechat.repository.UserRepository;
import com.example.livechat.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RegisterUserMapper registerUserMapper;
    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RegisterUserMapper registerUserMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.registerUserMapper = registerUserMapper;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserDTO register(RegisterUserDTO userDTO) {
        //String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        //userDTO.setPassword(encodedPassword);
        UserEntity userEntity = registerUserMapper.toEntity(userDTO);
        RegisterUserDTO res = registerUserMapper.toDTO(userRepository.save(userEntity));
        log.info(res.getId() + ", " + res.getUsername() + ", " + res.getEmail() + ", " + res.getPassword());
        return res;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        //String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        UserDTO foundUserByUsername = userMapper.toDTO(userRepository.findByUsername(userDTO.getUsername()));
        if(foundUserByUsername != null && foundUserByUsername.getPassword().equals(userDTO.getPassword())) {
            return foundUserByUsername;
        }
        throw new RuntimeException("Sorry! You have mistaken you username or password. Please, try again!");
    }
}