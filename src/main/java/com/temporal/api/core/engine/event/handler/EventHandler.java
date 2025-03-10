package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.neoforged.bus.api.IEventBus;

public interface EventHandler {
    void handle();

    default IEventBus getEventBus() {
        return InjectionContext.getFromInstance(IEventBus.class);
    }
}
