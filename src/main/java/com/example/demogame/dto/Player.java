package com.example.demogame.dto;

import lombok.Data;

@Data
public class Player {
    private String id;
    private int x;
    private int y;
    private String emoji;

    public Player() {
    }

    public Player(String id, int x, int y, String emoji) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.emoji = emoji;
    }
}