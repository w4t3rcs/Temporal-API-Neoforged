package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.event.data.ApiDataGenerator;
import com.temporal.api.core.event.data.DataGatherer;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class ClientDataEventHandler implements EventHandler {
    private static final DataGatherer GENERATOR = new ApiDataGenerator();

    @Override
    public void handle() {
        subscribeModEvent(GatherDataEvent.Client.class, GENERATOR::gatherData);
    }
}
