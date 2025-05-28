package com.example.demogame.websocket;

import com.example.demogame.dto.Item;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ItemGenerator {

    private final CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 5000) // 每5秒生成一个
    public void generateItem() {
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setX((int) (Math.random() * 700 + 50));
        item.setY((int) (Math.random() * 300 + 50));
        item.setType("coin");
        item.setEmoji("💰");

        items.add(item);

        // 发送给所有客户端
        messagingTemplate.convertAndSend("/topic/items", item);
    }

    public CopyOnWriteArrayList<Item> getItems() {
        return items;
    }

    public void removeItemById(String id) {
        items.removeIf(i -> i.getId().equals(id));
    }
}
