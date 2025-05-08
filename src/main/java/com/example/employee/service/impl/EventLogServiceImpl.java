package com.example.employee.service.impl;

import com.example.employee.model.EventLog;
import com.example.employee.repository.EventLogRepository;
import com.example.employee.service.EventLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventLogServiceImpl implements EventLogService {

    private final EventLogRepository eventLogRepository;

    public EventLogServiceImpl(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Override
    public void log(String action, String entity, String entityId, String details) {
        EventLog log = new EventLog();
        log.setAction(action);
        log.setEntity(entity);
        log.setEntityId(entityId);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());

        eventLogRepository.save(log);
    }
}
