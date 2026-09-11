package com.playmaker.ingestion.infrastructure.adapter.in.rest.dto;

import com.playmaker.ingestion.domain.model.GamePlay;
import java.time.Instant;
import java.util.UUID;

public record GamePlayResponse(
        UUID id,
        UUID gameId,
        String playerId,
        String teamId,
        String playType,
        int pointsAwarded,
        int gameClockSeconds,
        Instant createdAt
) {
    public static GamePlayResponse fromDomain(GamePlay play) {
        return new GamePlayResponse(
                play.getId(),
                play.getGameId(),
                play.getPlayerId(),
                play.getTeamId(),
                play.getPlayType(),
                play.getPointsAwarded(),
                play.getGameClockSeconds(),
                play.getCreatedAt()
        );
    }
}