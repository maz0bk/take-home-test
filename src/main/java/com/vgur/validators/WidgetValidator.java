package com.vgur.validators;

import com.vgur.dto.WidgetDto;
import com.vgur.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WidgetValidator {
    public void validate(WidgetDto widgetDto){
        List<String> errors = new ArrayList<>();
        if (widgetDto.getX()==null) errors.add("X Coordinate must be define");
        if (widgetDto.getY()==null) errors.add("Y Coordinate must be define");
        if (widgetDto.getHeight()==null || widgetDto.getHeight() < 1 ) errors.add("Widget height must be more than 0");
        if (widgetDto.getWidth()==null || widgetDto.getWidth() < 1 ) errors.add("Widget width must be more than 0");
        if (!errors.isEmpty()){throw new ValidationException(errors);}

    }
}
