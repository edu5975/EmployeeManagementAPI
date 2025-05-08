package com.example.employee.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "event_log")
public class EventLog {

    @Id
    private String id;
    private String action; // CREATE, READ, UPDATE, DELETE
    private String entity; // Employee
    private String entityId;
    private String details; // JSON o descripción
    private LocalDateTime timestamp;
}
