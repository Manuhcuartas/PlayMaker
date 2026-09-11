package com.playmaker.ingestion.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "game_plays")
public class GamePlayJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID gameId;

    @Column(nullable = false)
    private String playerId;

    @Column(nullable = false)
    private String teamId;

    @Column(nullable = false)
    private String playType;

    @Column(nullable = false)
    private int pointsAwarded;

    @Column(nullable = false)
    private int gameClockSeconds;

    @Column(nullable = false)
    private Instant createdAt;

    protected GamePlayJpaEntity() {}

    public GamePlayJpaEntity(UUID id, UUID gameId, String playerId, String teamId,
                             String playType, int pointsAwarded, int gameClockSeconds, Instant createdAt) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.teamId = teamId;
        this.playType = playType;
        this.pointsAwarded = pointsAwarded;
        this.gameClockSeconds = gameClockSeconds;
        this.createdAt = createdAt;
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