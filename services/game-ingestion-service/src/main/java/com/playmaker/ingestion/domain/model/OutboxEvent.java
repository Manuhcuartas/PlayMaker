package com.playmaker.ingestion.domain.model;

import java.time.Instant;
import java.util.UUID;

public class    OutboxEvent {
    private final UUID id;
    private final String aggregateType;
    private final String aggregateId;
    private final String eventType;
    private final String payload;
    private final Instant createdAt;

    public OutboxEvent(UUID id, String aggregateType, String aggregateId,
                       String eventType, String payload, Instant createdAt) {
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public static OutboxEvent create(String aggregateType, String aggregateId, String eventType, String payload) {
        return new OutboxEvent(UUID.randomUUID(), aggregateType, aggregateId, eventType, payload, Instant.now());
    }

    public UUID getId() { return id; }
    public String getAggregateType() { return aggregateType; }
    public String getAggregateId() { return aggregateId; }
    public String getEventType() { return eventType; }
    public String getPayload() { return payload; }
    public Instant getCreatedAt() { return createdAt; }
}