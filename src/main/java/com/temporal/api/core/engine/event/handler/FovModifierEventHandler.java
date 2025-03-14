package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.event.fov.BowFOVModifier;
import com.temporal.api.core.event.fov.FOVModifier;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public class FovModifierEventHandler implements EventHandler {
    public static final List<DeferredItem<?>> BOWS = new ArrayList<>();

    @Override
    public void handle() {
        getEventBus().addListener(ComputeFovModifierEvent.class, event -> {
            FOVModifier fovModifier = new BowFOVModifier();
            BOWS.forEach(bow -> fovModifier.modify(event, bow.get()));
        });
    }
}
