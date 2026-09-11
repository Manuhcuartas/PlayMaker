CREATE TABLE game_plays (
                            id UUID PRIMARY KEY,
                            game_id UUID NOT NULL,
                            player_id VARCHAR(64) NOT NULL,
                            team_id VARCHAR(64) NOT NULL,
                            play_type VARCHAR(32) NOT NULL,
                            points_awarded INT NOT NULL DEFAULT 0,
                            game_clock_seconds INT NOT NULL,
                            created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE outbox_events (
                               id UUID PRIMARY KEY,
                               aggregate_type VARCHAR(64) NOT NULL,
                               aggregate_id VARCHAR(64) NOT NULL,
                               event_type VARCHAR(64) NOT NULL,
                               payload JSONB NOT NULL,
                               status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                               retry_count INT NOT NULL DEFAULT 0,
                               created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
                               processed_at TIMESTAMP WITH TIME ZONE
);

CREATE INDEX idx_outbox_status_created ON outbox_events(status, created_at);
CREATE INDEX idx_game_plays_game_id ON game_plays(game_id);