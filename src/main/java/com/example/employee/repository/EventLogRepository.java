package com.example.employee.repository;

import com.example.employee.model.EventLog;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventLogRepository extends MongoRepository<EventLog, String> {
    List<EventLog> findByEntityIgnoreCase(String entity);

    List<EventLog> findByActionIgnoreCase(String action);

    List<EventLog> findByEntityIgnoreCaseAndActionIgnoreCase(String entity, String action);

}