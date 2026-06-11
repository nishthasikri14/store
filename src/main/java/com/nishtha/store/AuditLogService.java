package com.nishtha.store;

import org.springframework.stereotype.Service;

@Service
public class AuditLogService {

    private AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String entityName, int entityId, String operationType) {
        AuditLog auditLog = new AuditLog(entityName, entityId, operationType, "admin");
        auditLogRepository.save(auditLog);
    }
}