package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;

import java.util.function.Consumer;

public interface EventHandler {
    void handle();

    default IEventBus getEventBus() {
        return NeoForge.EVENT_BUS;
    }

    default IEventBus getModEventBus() {
        return InjectionContext.getFromInstance(IEventBus.class);
    }

    default <T extends Event> void subscribeEvent(Class<T> event, Consumer<T> consumer) {
        getEventBus().addListener(event, consumer);
    }

    default <T extends Event> void subscribeModEvent(Class<T> event, Consumer<T> consumer) {
        getModEventBus().addListener(event, consumer);
    }
}
