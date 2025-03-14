package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.event.data.ApiDataGenerator;
import com.temporal.api.core.event.data.DataGatherer;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class ClientDataEventHandler implements EventHandler {
    @Override
    public void handle() {
        subscribeModEvent(GatherDataEvent.Client.class, event -> {
            DataGatherer generator = new ApiDataGenerator();
            generator.gatherData(event);
        });
    }
}
