package com.vgur.services;

import com.vgur.model.Widget;
import com.vgur.repositories.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class WidgetService {
    private static AtomicLong idCounter = new AtomicLong();

    private final WidgetRepository widgetRepository;

    public Optional<Widget> findById(String id) {
        return widgetRepository.findById(id);
    }
    public static synchronized String createId() {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
