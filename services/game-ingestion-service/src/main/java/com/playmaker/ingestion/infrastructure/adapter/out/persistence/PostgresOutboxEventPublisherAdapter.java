package com.playmaker.ingestion.infrastructure.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playmaker.common.events.PlayRecordedEvent;
import com.playmaker.ingestion.application.port.out.EventPublisherPort;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity.OutboxEventJpaEntity;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.repository.SpringDataOutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class PostgresOutboxEventPublisherAdapter implements EventPublisherPort {

    private final SpringDataOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public PostgresOutboxEventPublisherAdapter(SpringDataOutboxRepository outboxRepository,
                                               ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(PlayRecordedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            OutboxEventJpaEntity outboxEntity = new OutboxEventJpaEntity(
                    event.eventId(),
                    "GAME",
                    event.gameId().toString(),
                    "PlayRecorded",
                    payload
            );
            outboxRepository.save(outboxEntity);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error serializando evento para outbox: " + event.eventId(), e);
        }
    }
}