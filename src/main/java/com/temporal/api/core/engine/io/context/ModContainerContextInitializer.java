package com.temporal.api.core.engine.io.context;

import net.neoforged.fml.ModContainer;

import java.util.List;

public class ModContainerContextInitializer implements ContextInitializer {
    @Override
    public void initialize(List<?> externalObjects) {
        if (externalObjects == null || externalObjects.isEmpty()) return;
        InjectionContext context = InjectionContext.getInstance();
        externalObjects.stream()
                .filter(o -> o instanceof ModContainer)
                .map(o -> (ModContainer)o)
                .forEach(context::putObject);
    }
}
