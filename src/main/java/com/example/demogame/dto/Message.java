package com.example.demogame.dto;

import lombok.Data;

@Data
public class Message {
    private String type; // 消息类型
    private String content; // 消息内容
    private String sender; // 发送者ID
    private String receiver; // 接收者ID（如果是私聊）
    private long timestamp; // 时间戳

    public Message() {
        this.timestamp = System.currentTimeMillis();
    }

    public Message(String type, String content, String sender, String receiver) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = System.currentTimeMillis();
    }
}
