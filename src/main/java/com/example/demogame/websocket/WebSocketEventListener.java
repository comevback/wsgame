package com.example.demogame.websocket;

import com.example.demogame.dto.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketEventListener {
    public static final Set<String> connectedPlayers = ConcurrentHashMap.newKeySet();
    public static final Map<String, String> sessionIdToPlayerId = new ConcurrentHashMap<>();
    public static final Map<String, Player> playerMap = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @EventListener
    public void handleConnect(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        connectedPlayers.add(sessionId);
        System.out.println("✅ 玩家连接: " + sessionId);
    }

    public static void registerPlayer(String sessionId, String playerId) {
        sessionIdToPlayerId.put(sessionId, playerId);
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        String playerId = sessionIdToPlayerId.remove(sessionId);

        if (playerId != null) {
            playerMap.remove(playerId); // 移除断开玩家
        }
        System.out.println("❌ 玩家断开: " + sessionId);
    }
}
