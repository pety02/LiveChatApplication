package com.example.livechat.service;

import com.example.livechat.dto.MessageDTO;
import com.example.livechat.entities.MessageEntity;
import com.example.livechat.mapper.MessageMapper;
import com.example.livechat.repository.MessageRepository;
import com.example.livechat.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        MessageEntity messageEntity = messageMapper.toEntity(messageDTO);
        return messageMapper.toDTO(messageRepository.save(messageEntity));
    }
}
