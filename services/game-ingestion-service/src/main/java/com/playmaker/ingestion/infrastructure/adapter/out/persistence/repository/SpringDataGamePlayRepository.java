package com.playmaker.ingestion.infrastructure.adapter.out.persistence.repository;

import com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity.GamePlayJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataGamePlayRepository extends JpaRepository<GamePlayJpaEntity, UUID> {}