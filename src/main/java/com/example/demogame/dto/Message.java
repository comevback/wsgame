package com.example.demogame.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String content; // 消息内容
    private String sender; // 发送者ID

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }
}
