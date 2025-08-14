package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.collection.TemporalQueue;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Queue;
import java.util.function.Consumer;

public class EntityRendererRegisterRendererEventHandler implements EventHandler {
    public static final Queue<Consumer<EntityRenderersEvent.RegisterRenderers>> RENDERING_REGISTRIES = new TemporalQueue<>();

    @Override
    public void handle() {
        subscribeModEvent(EntityRenderersEvent.RegisterRenderers.class, event -> {
            RENDERING_REGISTRIES.forEach(consumer -> consumer.accept(event));
        });
    }
}
