package com.playmaker.ingestion.infrastructure.adapter.out.persistence;

import com.playmaker.ingestion.application.port.out.GamePlayRepositoryPort;
import com.playmaker.ingestion.domain.model.GamePlay;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity.GamePlayJpaEntity;
import com.playmaker.ingestion.infrastructure.adapter.out.persistence.repository.SpringDataGamePlayRepository;
import org.springframework.stereotype.Component;

@Component
public class PostgresGamePlayRepositoryAdapter implements GamePlayRepositoryPort {

    private final SpringDataGamePlayRepository repository;

    public PostgresGamePlayRepositoryAdapter(SpringDataGamePlayRepository repository) {
        this.repository = repository;
    }

    @Override
    public GamePlay save(GamePlay play) {
        GamePlayJpaEntity entity = new GamePlayJpaEntity(
                play.getId(),
                play.getGameId(),
                play.getPlayerId(),
                play.getTeamId(),
                play.getPlayType(),
                play.getPointsAwarded(),
                play.getGameClockSeconds(),
                play.getCreatedAt()
        );
        GamePlayJpaEntity saved = repository.save(entity);
        return new GamePlay(
                saved.getId(),
                saved.getGameId(),
                saved.getPlayerId(),
                saved.getTeamId(),
                saved.getPlayType(),
                saved.getPointsAwarded(),
                saved.getGameClockSeconds(),
                saved.getCreatedAt()
        );
    }
}