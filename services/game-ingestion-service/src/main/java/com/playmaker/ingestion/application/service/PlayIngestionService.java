package com.playmaker.ingestion.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playmaker.common.events.PlayRecordedEvent;
import com.playmaker.ingestion.application.port.in.IngestPlayUseCase;
import com.playmaker.ingestion.application.port.in.RecordPlayCommand;
import com.playmaker.ingestion.application.port.out.GamePlayRepositoryPort;
import com.playmaker.ingestion.application.port.out.OutboxRepositoryPort;
import com.playmaker.ingestion.domain.model.GamePlay;
import com.playmaker.ingestion.domain.model.OutboxEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PlayIngestionService implements IngestPlayUseCase {

    private final GamePlayRepositoryPort gamePlayRepositoryPort;
    private final OutboxRepositoryPort outboxRepositoryPort;
    private final ObjectMapper objectMapper;

    public PlayIngestionService(GamePlayRepositoryPort gamePlayRepositoryPort,
                                OutboxRepositoryPort outboxRepositoryPort,
                                ObjectMapper objectMapper) {
        this.gamePlayRepositoryPort = gamePlayRepositoryPort;
        this.outboxRepositoryPort = outboxRepositoryPort;
        this.objectMapper = objectMapper;
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

        try {
            String payload = objectMapper.writeValueAsString(event);
            OutboxEvent outbox = OutboxEvent.create(
                    "GAME",
                    savedPlay.getGameId().toString(),
                    "PlayRecorded",
                    payload
            );
            outboxRepositoryPort.save(outbox);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize outbox payload", e);
        }

        return savedPlay;
    }
}