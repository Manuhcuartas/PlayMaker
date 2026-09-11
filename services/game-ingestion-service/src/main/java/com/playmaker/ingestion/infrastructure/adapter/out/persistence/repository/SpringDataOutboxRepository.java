package com.playmaker.ingestion.infrastructure.adapter.out.persistence.repository;

import com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity.OutboxEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface SpringDataOutboxRepository extends JpaRepository<OutboxEventJpaEntity, UUID> {
    @Query(value = """
        SELECT * FROM outbox_events 
        WHERE status = 'PENDING' 
        ORDER BY created_at ASC 
        LIMIT 50 
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    List<OutboxEventJpaEntity> findPendingEventsForProcessing();
}