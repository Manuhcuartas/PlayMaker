package com.playmaker.ingestion.application.port.out;

import com.playmaker.common.events.PlayRecordedEvent;

public interface EventPublisherPort {
    void publish(PlayRecordedEvent event);
}