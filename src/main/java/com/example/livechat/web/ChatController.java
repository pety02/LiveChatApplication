package com.example.livechat.web;

import com.example.livechat.dtoes.MessageDTO;
import com.example.livechat.service.interfaces.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatController {
    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO message) {
        if (message.getFromUser() == null || message.getToUser() == null || message.getContent() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        System.out.println("Received Message: " + message);
        messageService.sendMessage(message);

        return ResponseEntity.ok("Message saved successfully!");
    }
}