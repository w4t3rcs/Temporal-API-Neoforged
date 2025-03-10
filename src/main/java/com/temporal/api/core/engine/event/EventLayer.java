package com.temporal.api.core.engine.event;

import com.temporal.api.core.engine.EngineLayer;
import com.temporal.api.core.engine.event.handler.ClientDataEventHandler;
import com.temporal.api.core.engine.event.handler.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class EventLayer implements EngineLayer {
    private List<EventHandler> additionalEventHandlers;

    @Override
    public void processAllTasks() {
        List<EventHandler> eventHandlers = new ArrayList<>(List.of(new ClientDataEventHandler()));
        eventHandlers.addAll(additionalEventHandlers);
        eventHandlers.forEach(EventHandler::handle);
    }

    public void setAdditionalEventHandlers(List<EventHandler> additionalEventHandlers) {
        this.additionalEventHandlers = additionalEventHandlers;
    }
}
