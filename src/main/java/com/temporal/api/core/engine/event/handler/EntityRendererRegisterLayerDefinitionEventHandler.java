package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.collection.TemporalMap;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;

public class EntityRendererRegisterLayerDefinitionEventHandler implements EventHandler {
    public static Map<ModelLayerLocation, LayerDefinition> LAYERS = new TemporalMap<>();

    @Override
    public void handle() {
        subscribeModEvent(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> {
            LAYERS.forEach((location, definition) -> {
                event.registerLayerDefinition(location, () -> definition);
            });
        });
    }
}
