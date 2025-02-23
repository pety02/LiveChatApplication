package com.example.livechat.service.interfaces;

import com.example.livechat.dtoes.MessageDTO;

public interface MessageService {
    MessageDTO sendMessage(MessageDTO messageDTO);
}