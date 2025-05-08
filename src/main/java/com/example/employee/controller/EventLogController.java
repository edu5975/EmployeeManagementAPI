package com.example.employee.controller;

import com.example.employee.model.EventLog;
import com.example.employee.repository.EventLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-log")
public class EventLogController {

    private final EventLogRepository eventLogRepository;

    public EventLogController(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    // Obtener todos los eventos
    @GetMapping
    public List<EventLog> getAllEvents() {
        return eventLogRepository.findAll();
    }

    // Obtener eventos por entidad (e.g., "Employee")
    @GetMapping("/entity/{entity}")
    public List<EventLog> getEventsByEntity(@PathVariable String entity) {
        return eventLogRepository.findByEntityIgnoreCase(entity);
    }

    // Obtener eventos por tipo de acción (e.g., "CREATE")
    @GetMapping("/action/{action}")
    public List<EventLog> getEventsByAction(@PathVariable String action) {
        return eventLogRepository.findByActionIgnoreCase(action);
    }

    // Obtener eventos por entidad y acción
    @GetMapping("/entity/{entity}/action/{action}")
    public List<EventLog> getEventsByEntityAndAction(
            @PathVariable String entity,
            @PathVariable String action) {
        return eventLogRepository.findByEntityIgnoreCaseAndActionIgnoreCase(entity, action);
    }
}
