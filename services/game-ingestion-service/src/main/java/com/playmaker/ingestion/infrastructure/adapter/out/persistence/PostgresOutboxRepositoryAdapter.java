package com.playmaker.ingestion.infrastructure.adapter.out.persistence;

import com.playmaker.ingestion.application.port.out.OutboxRepositoryPort;
import com.playmaker.ingestion.domain.model.OutboxEvent;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity.OutboxEventJpaEntity;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.repository.SpringDataOutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class PostgresOutboxRepositoryAdapter implements OutboxRepositoryPort {

    private final SpringDataOutboxRepository repository;

    public PostgresOutboxRepositoryAdapter(SpringDataOutboxRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(OutboxEvent outboxEvent) {
        OutboxEventJpaEntity entity = new OutboxEventJpaEntity(
                outboxEvent.getId(),
                outboxEvent.getAggregateType(),
                outboxEvent.getAggregateId(),
                outboxEvent.getEventType(),
                outboxEvent.getPayload()
        );
        repository.save(entity);
    }
}