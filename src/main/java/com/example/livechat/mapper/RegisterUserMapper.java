package com.example.livechat.mapper;

import com.example.livechat.dto.RegisterUserDTO;
import com.example.livechat.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {
    //@Mapping(target = "confirmPassword", ignore = true)
    RegisterUserDTO toDTO(UserEntity userEntity);
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(RegisterUserDTO registerUserDTO);
}