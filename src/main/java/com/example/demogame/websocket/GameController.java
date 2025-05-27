package com.example.demogame.websocket;

import com.example.demogame.dto.Message;
import com.example.demogame.dto.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/move")         // 客户端发送到 /app/move
    @SendTo("/topic/players")        // 广播到 /topic/players
    public Player handlePlayerMove(@Payload Player player) {
        // 收到一个玩家移动，原样广播回所有人
        return player;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message MessageCenter(@Payload Message message){
        return message;
    }
}
