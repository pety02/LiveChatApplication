package com.example.livechat.mapper;

import com.example.livechat.dto.MessageDTO;
import com.example.livechat.entites.MessageEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MessageMapper {
    MessageDTO toDTO(MessageEntity messageEntity);
    MessageEntity toEntity(MessageDTO messageDTO);
}
