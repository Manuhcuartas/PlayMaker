package com.playmaker.ingestion.application.port.out;

import com.playmaker.ingestion.domain.model.GamePlay;

public interface GamePlayRepositoryPort {
    GamePlay save(GamePlay gamePlay);
}