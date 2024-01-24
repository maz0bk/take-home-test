package com.vgur.dto;

import lombok.Getter;

@Getter
public class WidgetDto {
    private final Integer zIndex;
    private final Integer x;
    private final Integer y;
    private final Integer width;
    private final Integer height;

    public WidgetDto(Integer zIndex, Integer x, Integer y, Integer width, Integer height) {
        this.zIndex = zIndex;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
