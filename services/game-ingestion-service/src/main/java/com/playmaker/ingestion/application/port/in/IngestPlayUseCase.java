package com.playmaker.ingestion.application.port.in;

import com.playmaker.ingestion.domain.model.GamePlay;

public interface IngestPlayUseCase {
    GamePlay handle(RecordPlayCommand command);
}