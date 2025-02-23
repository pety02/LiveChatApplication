package com.example.livechat.dtoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Integer id;
    private Integer fromUser;
    private Integer toUser;
    private Timestamp sentDate;
    private String content;
}