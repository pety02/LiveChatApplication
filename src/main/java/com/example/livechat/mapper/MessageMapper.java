package com.example.livechat.mapper;

import com.example.livechat.dto.MessageDTO;
import com.example.livechat.entities.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDTO toDTO(MessageEntity messageEntity);
    @Mapping(target = "id", ignore = true)
    MessageEntity toEntity(MessageDTO messageDTO);
}
