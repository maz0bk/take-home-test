package com.vgur.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WidgetDto {
    private final String id;
    private final Integer zIndex;
    private final Integer x;
    private final Integer y;
    private final Integer width;
    private final Integer height;
    private final LocalDateTime updatedAt;

    public WidgetDto(String id, Integer zIndex, Integer x, Integer y, Integer width, Integer height, LocalDateTime updatedAt) {
        this.id = id;
        this.zIndex = zIndex;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.updatedAt = updatedAt;
    }
}
