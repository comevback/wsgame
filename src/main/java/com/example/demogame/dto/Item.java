package com.example.demogame.dto;

import lombok.Data;

@Data
public class Item {
    private String id;    // 道具唯一ID
    private int x;
    private int y;
    private String emoji;
}
