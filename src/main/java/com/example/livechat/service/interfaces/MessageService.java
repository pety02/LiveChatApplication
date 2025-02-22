package com.example.livechat.service.interfaces;

import com.example.livechat.dto.MessageDTO;

public interface MessageService {
    MessageDTO sendMessage(MessageDTO messageDTO);
}