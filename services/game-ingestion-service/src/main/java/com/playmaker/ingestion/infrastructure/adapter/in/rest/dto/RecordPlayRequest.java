package com.playmaker.ingestion.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordPlayRequest(
        @NotBlank(message = "playerId is required")
        String playerId,

        @NotBlank(message = "teamId is required")
        String teamId,

        @NotBlank(message = "playType is required")
        String playType,

        @Min(value = 0, message = "pointsAwarded cannot be negative")
        int pointsAwarded,

        @NotNull(message = "gameClockSeconds is required")
        @Min(value = 0, message = "gameClockSeconds cannot be negative")
        Integer gameClockSeconds
) {}