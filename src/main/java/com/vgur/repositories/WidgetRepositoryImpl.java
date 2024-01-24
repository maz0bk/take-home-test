package com.vgur.repositories;

import com.vgur.model.Widget;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {
    private Map<String, Widget> widgets;

    public WidgetRepositoryImpl() {
        this.widgets = new HashMap<>();
    }

    @Override
    public Optional<Widget> findById(String id) {
        return Optional.of(widgets.get(id));
    }

    @Override
    public List<Widget> getAll() {
        if (widgets.isEmpty()) return Collections.emptyList();

        return widgets.values().stream().toList();
    }

      @Override
    public void add(Widget widget) {
        widgets.put(widget.getId(),widget);
    }
}
