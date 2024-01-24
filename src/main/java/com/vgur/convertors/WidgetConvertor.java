package com.vgur.convertors;

import com.vgur.dto.WidgetDto;
import com.vgur.model.Widget;
import org.springframework.stereotype.Service;

@Service
public class WidgetConvertor {
    public WidgetDto entityToDto(Widget widget) {
        return new WidgetDto(widget.getZIndex(), widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight());
    }
}
