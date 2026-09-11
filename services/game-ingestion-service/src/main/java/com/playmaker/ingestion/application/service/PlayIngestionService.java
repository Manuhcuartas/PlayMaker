package com.playmaker.ingestion.application.service;

import com.playmaker.common.events.PlayRecordedEvent;
import com.playmaker.ingestion.application.port.in.IngestPlayUseCase;
import com.playmaker.ingestion.application.port.in.RecordPlayCommand;
import com.playmaker.ingestion.application.port.out.EventPublisherPort;
import com.playmaker.ingestion.application.port.out.GamePlayRepositoryPort;
import com.playmaker.ingestion.domain.model.GamePlay;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PlayIngestionService implements IngestPlayUseCase {

    private final GamePlayRepositoryPort gamePlayRepositoryPort;
    private final EventPublisherPort eventPublisherPort;

    public PlayIngestionService(GamePlayRepositoryPort gamePlayRepositoryPort,
                                EventPublisherPort eventPublisherPort) {
        this.gamePlayRepositoryPort = gamePlayRepositoryPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    @Transactional
    public GamePlay handle(RecordPlayCommand command) {
        GamePlay play = GamePlay.createNew(
                command.gameId(),
                command.playerId(),
                command.teamId(),
                command.playType(),
                command.pointsAwarded(),
                command.gameClockSeconds()
        );

        GamePlay savedPlay = gamePlayRepositoryPort.save(play);

        PlayRecordedEvent event = new PlayRecordedEvent(
                UUID.randomUUID(),
                savedPlay.getId(),
                savedPlay.getGameId(),
                savedPlay.getPlayerId(),
                savedPlay.getTeamId(),
                savedPlay.getPlayType(),
                savedPlay.getPointsAwarded(),
                savedPlay.getGameClockSeconds(),
                savedPlay.getCreatedAt()
        );

        eventPublisherPort.publish(event);

        return savedPlay;
    }
}