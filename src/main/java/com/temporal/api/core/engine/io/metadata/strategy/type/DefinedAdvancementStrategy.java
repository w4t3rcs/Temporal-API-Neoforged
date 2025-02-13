package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.annotation.DefinedAdvancement;
import com.temporal.api.core.event.data.advancement.AdvancementGenerationHolder;
import com.temporal.api.core.event.data.advancement.ApiAdvancementProvider;

import java.lang.reflect.Constructor;

public class DefinedAdvancementStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(DefinedAdvancement.class)) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            AdvancementGenerationHolder advancementGenerationHolder = (AdvancementGenerationHolder) constructor.newInstance();
            ApiAdvancementProvider.ADVANCEMENTS.add(advancementGenerationHolder);
        }
    }
}
