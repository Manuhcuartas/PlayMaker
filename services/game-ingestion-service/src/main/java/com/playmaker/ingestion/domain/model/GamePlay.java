package com.playmaker.ingestion.domain.model;

import java.time.Instant;
import java.util.UUID;

public class GamePlay {
    private final UUID id;
    private final UUID gameId;
    private final String playerId;
    private final String teamId;
    private final String playType;
    private final int pointsAwarded;
    private final int gameClockSeconds;
    private final Instant createdAt;

    public GamePlay(UUID id, UUID gameId, String playerId, String teamId,
                    String playType, int pointsAwarded, int gameClockSeconds, Instant createdAt) {
        if (pointsAwarded < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
        if (gameClockSeconds < 0) {
            throw new IllegalArgumentException("Clock seconds cannot be negative");
        }
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.teamId = teamId;
        this.playType = playType;
        this.pointsAwarded = pointsAwarded;
        this.gameClockSeconds = gameClockSeconds;
        this.createdAt = createdAt;
    }

    public static GamePlay createNew(UUID gameId, String playerId, String teamId,
                                     String playType, int pointsAwarded, int gameClockSeconds) {
        return new GamePlay(
                UUID.randomUUID(),
                gameId,
                playerId,
                teamId,
                playType,
                pointsAwarded,
                gameClockSeconds,
                Instant.now()
        );
    }

    public UUID getId() { return id; }
    public UUID getGameId() { return gameId; }
    public String getPlayerId() { return playerId; }
    public String getTeamId() { return teamId; }
    public String getPlayType() { return playType; }
    public int getPointsAwarded() { return pointsAwarded; }
    public int getGameClockSeconds() { return gameClockSeconds; }
    public Instant getCreatedAt() { return createdAt; }
}