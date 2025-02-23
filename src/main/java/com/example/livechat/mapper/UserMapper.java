package com.example.livechat.mapper;

import com.example.livechat.dtoes.UserDTO;
import com.example.livechat.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserEntity userEntity);
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDTO userDTO);
}
