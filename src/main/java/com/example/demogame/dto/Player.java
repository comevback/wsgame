package com.example.demogame.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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