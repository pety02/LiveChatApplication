package com.example.livechat.mapper;

import com.example.livechat.dtoes.RegisterUserDTO;
import com.example.livechat.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {
    @Mapping(source = "id", target = "id") // Explicitly map 'id' field
    @Mapping(source = "username", target = "username") // Explicit mapping (optional if names match)
    @Mapping(source = "email", target = "email") // Explicit mapping (optional if names match)
    @Mapping(source = "password", target = "password") // Explicit mapping (optional if names match)
    @Mapping(source = "password", target = "confirmPassword")
    RegisterUserDTO toDTO(UserEntity userEntity);
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(RegisterUserDTO registerUserDTO);
}