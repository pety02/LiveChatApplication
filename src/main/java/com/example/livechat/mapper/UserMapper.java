package com.example.livechat.mapper;

import com.example.livechat.dto.UserDTO;
import com.example.livechat.entites.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toDTO(UserEntity userEntity);
    UserEntity toEntity(UserDTO userDTO);
}
