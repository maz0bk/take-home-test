package com.vgur.model;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class Widget {

    private final String id;
    private Integer zIndex;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private LocalDateTime updatedAt;

    public Widget(String id,Integer zIndex, Integer x, Integer y, Integer width, Integer height) {
        this.id = id;
        this.zIndex = zIndex;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
