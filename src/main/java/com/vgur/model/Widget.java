package com.vgur.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Widget implements Comparable<Widget> {

    private final String id;
    private Integer zIndex;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private LocalDateTime updatedAt;

    @Override
    public int compareTo(Widget other) {
        return Integer.compare(this.zIndex, other.getZIndex());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return id.equals(((Widget)obj).getId());
    }
}
