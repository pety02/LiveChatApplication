package com.example.livechat.service;

import com.example.livechat.dto.UserDTO;
import com.example.livechat.entites.UserEntity;
import com.example.livechat.mapper.UserMapper;
import com.example.livechat.repository.UserRepository;
import com.example.livechat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        UserEntity userEntity = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        UserDTO foundUserByUsername = userMapper.toDTO(userRepository.findByUsername(userDTO.getUsername()));
        if(foundUserByUsername != null && foundUserByUsername.getPassword().equals(encodedPassword)) {
            return foundUserByUsername;
        }
        throw new RuntimeException("Sorry! You have mistaken you username or password. Please, try again!");
    }
}