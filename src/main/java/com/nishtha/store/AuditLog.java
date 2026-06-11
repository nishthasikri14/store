package com.nishtha.store;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String entityName;
    private int entityId;
    private String operationType;
    private String userName;
    private LocalDateTime eventTimestamp;

    public AuditLog() {}

    public AuditLog(String entityName, int entityId, String operationType, String userName) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.operationType = operationType;
        this.userName = userName;
        this.eventTimestamp = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getEntityName() { return entityName; }
    public int getEntityId() { return entityId; }
    public String getOperationType() { return operationType; }
    public String getUserName() { return userName; }
    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
}