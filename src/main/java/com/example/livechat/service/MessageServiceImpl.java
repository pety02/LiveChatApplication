package com.example.livechat.service;

import com.example.livechat.dtoes.MessageDTO;
import com.example.livechat.entities.MessageEntity;
import com.example.livechat.mapper.MessageMapper;
import com.example.livechat.repository.MessageRepository;
import com.example.livechat.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(messageDTO);
        System.out.println(messageEntity.getId() + ", " + messageEntity.getFromUser() + ", " + messageEntity.getToUser() + ", " + messageEntity.getSentDate() + ", " + messageEntity.getContent());
        return messageMapper.toDTO(messageRepository.save(messageEntity));
    }

    @Override
    public List<MessageDTO> fetchAllUserMessages(Integer userID, Integer friendID) {
        List<MessageEntity> messageEntities = messageRepository.findAllByUserIDAndFriendID(userID, friendID);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(MessageEntity messageEntity : messageEntities) {
            messageDTOS.add(messageMapper.toDTO(messageEntity));
        }

        return messageDTOS;
    }
}
