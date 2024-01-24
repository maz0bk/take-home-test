package com.vgur.repositories;

import com.vgur.model.Widget;
import com.vgur.services.WidgetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WidgetRepositoryTest {
    private static final Widget FIRSTWIDGET = new Widget(WidgetService.createId(), 1, 1, 1, 10, 10);

    @Test
    void getById() {
        var widgetRepository = prepareWidgetRepository();
        var widget = widgetRepository.findById(FIRSTWIDGET.getId());
        assertThat(widget.get()).isEqualTo(FIRSTWIDGET);
    }

    @Test
    void getAllWhenNoElements() {
        var widgetRepository = new WidgetRepositoryImpl();
        var widgetsList = widgetRepository.getAll();
        assertThat(widgetsList.isEmpty()).isTrue();
    }

    @Test
    void getAllElements() {
        var widgetRepository = prepareWidgetRepository();
        var widgetList = widgetRepository.getAll();
        Assertions.assertAll(() -> assertThat(widgetList).isNotEmpty(),
                () -> assertThat(widgetList.contains(FIRSTWIDGET)).isTrue());
    }

    private WidgetRepository prepareWidgetRepository() {
        var widgetRepository = new WidgetRepositoryImpl();
        widgetRepository.add(FIRSTWIDGET);
        return widgetRepository;
    }
}