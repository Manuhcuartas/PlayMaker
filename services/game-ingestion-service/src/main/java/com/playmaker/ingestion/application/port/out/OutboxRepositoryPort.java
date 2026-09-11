package com.playmaker.ingestion.application.port.out;

import com.playmaker.ingestion.domain.model.OutboxEvent;

public interface OutboxRepositoryPort {
    void save(OutboxEvent outboxEvent);
}