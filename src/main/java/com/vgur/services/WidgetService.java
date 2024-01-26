package com.vgur.services;

import com.vgur.dto.WidgetDto;
import com.vgur.exceptions.ResourceNotFoundException;
import com.vgur.model.Widget;
import com.vgur.repositories.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class WidgetService {
    private static AtomicLong idCounter = new AtomicLong();
    private final WidgetRepository widgetRepository;
    private Map<Integer, Widget> zIndexMap = new TreeMap<>();

    private static synchronized String createId() {
        return String.valueOf(idCounter.getAndIncrement());
    }

    public Optional<Widget> findById(String id) {
        return widgetRepository.findById(id);
    }

    public Widget addNewWidget(WidgetDto widgetDto) {
        var widget = Widget.builder()
                .id(createId())
                .x(widgetDto.getX())
                .y(widgetDto.getY())
                .height(widgetDto.getHeight())
                .width(widgetDto.getWidth())
                .updatedAt(LocalDateTime.now())
                .build();
        addIndex(widget, widgetDto.getZIndex());
        widgetRepository.add(widget);
        return widget;

    }

    private void addIndex(Widget widget, final Integer zIndex) {
        var zIndexNew = zIndex;
        if (zIndex == null) {
            zIndexNew = zIndexMap.keySet().stream().max(Integer::compare).orElseGet(() -> 0) + 1;
            widget.setZIndex(zIndexNew);
            zIndexMap.put(zIndexNew, widget);
        } else if(!zIndexMap.containsKey(zIndex)){
            widget.setZIndex(zIndexNew);
            zIndexMap.put(zIndexNew, widget);
        } else  {
            changeZIndexes(widget, zIndex);
        }
    }

    private void changeZIndexes(Widget widget, Integer zIndex) {
        var keysList = zIndexMap.keySet().stream().filter(key -> key >= zIndex).toList();
        Widget tmpWidget = null;
        Widget tmpWidget2 = null;
        Integer previousKey = null;
        for (Integer key : keysList) {
            if (previousKey == null) {
                tmpWidget = zIndexMap.get(key);
                previousKey = key;
                widget.setZIndex(key);
                zIndexMap.put(key, widget);
            } else {
                if (key - previousKey > 1) {
                    tmpWidget.setZIndex(previousKey + 1);
                    zIndexMap.put(tmpWidget.getZIndex(), tmpWidget);
                    tmpWidget2 = null;
                    break;
                } else {
                    tmpWidget.setZIndex(key);
                    tmpWidget2 = zIndexMap.get(key);
                    zIndexMap.put(key, tmpWidget);
                    previousKey = key;
                    tmpWidget = tmpWidget2;
                }
            }
        }
        if (tmpWidget2 != null){
            tmpWidget2.setZIndex(previousKey + 1);
            zIndexMap.put(tmpWidget2.getZIndex(), tmpWidget2);
        }
    }

    public Widget update(WidgetDto widgetDto) {
        var widget = widgetRepository.findById(widgetDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Can not update widget, widget not found, id: " + widgetDto.getId()));
        widget.setUpdatedAt(LocalDateTime.now());
        widget.setX(widgetDto.getX());
        widget.setY(widgetDto.getY());
        widget.setWidth(widgetDto.getWidth());
        widget.setHeight(widgetDto.getHeight());
        if (!widget.getZIndex().equals(widgetDto.getZIndex())) {
            addIndex(widget, widgetDto.getZIndex());
        }
        return widget;
    }

    public List<Widget> getAll() {
        return widgetRepository.getAll();
    }

    public void deleteById(String id) {
        widgetRepository.deleteById(id);
    }
}
