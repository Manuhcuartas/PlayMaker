package com.playmaker.common.events;

import java.time.Instant;
import java.util.UUID;

public record PlayRecordedEvent(
        UUID eventId,
        UUID playId,
        UUID gameId,
        String playerId,
        String teamId,
        String playType,
        int pointsAwarded,
        int gameClockSeconds,
        Instant occurredAt
) {}