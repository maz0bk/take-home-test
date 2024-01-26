package com.vgur.controllers;


import com.vgur.convertors.WidgetConvertor;
import com.vgur.dto.WidgetDto;
import com.vgur.exceptions.ResourceNotFoundException;
import com.vgur.model.Widget;
import com.vgur.services.WidgetService;
import com.vgur.validators.WidgetValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/widgets")
@RequiredArgsConstructor
public class WidgetController {
    private final WidgetService widgetService;
    private final WidgetConvertor widgetConvertor;
    private final WidgetValidator widgetValidator;

    @GetMapping("/{id}")
    public WidgetDto getWidgetById(@PathVariable String id) {
        var widget = widgetService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Widget not found, id: " + id));
        return widgetConvertor.entityToDto(widget);
    }
    @GetMapping
    public List<Widget> getAllWidgets(){
        return widgetService.getAll();
    }

    @PostMapping
    public WidgetDto addNewWidget(@RequestBody WidgetDto widgetDto) {
        widgetValidator.validate(widgetDto);
        var widget =  widgetService.addNewWidget(widgetDto);
        return widgetConvertor.entityToDto(widget);
    }
    @PutMapping
    public WidgetDto updateWidget(@RequestBody WidgetDto widgetDto){
        widgetValidator.validate(widgetDto);
        var widget  = widgetService.update(widgetDto);
        return widgetConvertor.entityToDto(widget);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        widgetService.deleteById(id);
    }
}
