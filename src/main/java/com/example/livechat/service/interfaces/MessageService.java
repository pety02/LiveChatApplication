package com.example.livechat.service.interfaces;

import com.example.livechat.dtoes.MessageDTO;
import java.util.List;

public interface MessageService {
    MessageDTO sendMessage(MessageDTO messageDTO);
    List<MessageDTO> fetchAllUserMessages(Integer userID, Integer friendID);
}