package com.vgur.repositories;

import com.vgur.model.Widget;

import java.util.List;
import java.util.Optional;

public interface WidgetRepository {
    Optional<Widget> findById(String id);
    List<Widget> getAll();
     void add(Widget widget);


    void deleteById(String id);
}
