package com.playmaker.ingestion.application.port.in;

import java.util.UUID;

public record RecordPlayCommand(
        UUID gameId,
        String playerId,
        String teamId,
        String playType,
        int pointsAwarded,
        int gameClockSeconds
) {}