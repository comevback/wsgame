package com.example.demogame.websocket;

import com.example.demogame.dto.Message;
import com.example.demogame.dto.Player;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

//    @MessageMapping("/move")         // 客户端发送到 /app/move
//    @SendTo("/topic/players")        // 广播到 /topic/players
//    public Player handlePlayerMove(Player player) {
//        // 收到一个玩家移动，原样广播回所有人
//        return player;
//    }
    @MessageMapping("/move")
    @SendTo("/topic/players")
    public List<Player> handlePlayerMove(Player player, MessageHeaders headers) {
        String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
        WebSocketEventListener.registerPlayer(sessionId, player.getId());

        // 更新该玩家的位置
        WebSocketEventListener.playerMap.put(player.getId(), player);

        // 返回所有玩家列表
        return new ArrayList<>(WebSocketEventListener.playerMap.values());
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message MessageCenter(Message message){
        return message;
    }

//    @GetMapping("/player/count")
//    @ResponseBody
    @Scheduled(fixedRate = 1000) // 每1秒更新一次玩家数量
    @SendTo("/topic/count")
    public int getPlayerCount() {
        return WebSocketEventListener.connectedPlayers.size();
    }
}
