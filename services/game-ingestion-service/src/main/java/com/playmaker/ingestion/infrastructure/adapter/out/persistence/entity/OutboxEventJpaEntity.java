package com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
public class OutboxEventJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String aggregateType;

    @Column(nullable = false)
    private String aggregateId;

    @Column(nullable = false)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private String payload;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int retryCount;

    @Column(nullable = false)
    private Instant createdAt;

    @Column
    private Instant processedAt;

    protected OutboxEventJpaEntity() {}

    public OutboxEventJpaEntity(UUID id, String aggregateType, String aggregateId, String eventType, String payload) {
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.status = "PENDING";
        this.retryCount = 0;
        this.createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public String getAggregateId() { return aggregateId; }
    public String getPayload() { return payload; }
    public void markAsPublished() {
        this.status = "PUBLISHED";
        this.processedAt = Instant.now();
    }
}