package com.playmaker.ingestion.infrastructure.adapter.in.rest;

import com.playmaker.ingestion.application.port.in.IngestPlayUseCase;
import com.playmaker.ingestion.application.port.in.RecordPlayCommand;
import com.playmaker.ingestion.domain.model.GamePlay;
import com.playmaker.ingestion.infrastructure.adapter.in.rest.dto.GamePlayResponse;
import com.playmaker.ingestion.infrastructure.adapter.in.rest.dto.RecordPlayRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games/{gameId}/plays")
public class GamePlayController {

    private final IngestPlayUseCase ingestPlayUseCase;

    public GamePlayController(IngestPlayUseCase ingestPlayUseCase) {
        this.ingestPlayUseCase = ingestPlayUseCase;
    }

    @PostMapping
    public ResponseEntity<GamePlayResponse> recordPlay(
            @PathVariable UUID gameId,
            @Valid @RequestBody RecordPlayRequest request) {

        RecordPlayCommand command = new RecordPlayCommand(
                gameId,
                request.playerId(),
                request.teamId(),
                request.playType(),
                request.pointsAwarded(),
                request.gameClockSeconds()
        );

        GamePlay createdPlay = ingestPlayUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(GamePlayResponse.fromDomain(createdPlay));
    }
}