package com.temporal.api.core.engine.io.context;

import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;

import java.util.List;

public class EventBusContextInitializer implements ContextInitializer {
    @Override
    public void initialize(List<?> externalObjects) {
        if (externalObjects == null || externalObjects.isEmpty()) return;
        Context context = InjectionContext.getInstance();
        externalObjects.stream()
                .filter(o -> o instanceof IEventBus)
                .map(o -> (EventBus)o)
                .forEach(context::putObject);
    }
}
