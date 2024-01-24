package com.vgur.controllers;


import com.vgur.convertors.WidgetConvertor;
import com.vgur.dto.WidgetDto;
import com.vgur.exceptions.ResourceNotFoundException;
import com.vgur.services.WidgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/widgets")
@RequiredArgsConstructor
public class WidgetController {
    private final WidgetService widgetService;
    private final WidgetConvertor widgetConvertor;

    @GetMapping("/{id}")
    public WidgetDto getWidgetById(@PathVariable String id) {
        var widget = widgetService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Widget not found, id: " + id));
        return widgetConvertor.entityToDto(widget);
    }
}
