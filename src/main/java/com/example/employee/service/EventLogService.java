package com.example.employee.service;

public interface EventLogService {
    void log(String action, String entity, String entityId, String details);
}