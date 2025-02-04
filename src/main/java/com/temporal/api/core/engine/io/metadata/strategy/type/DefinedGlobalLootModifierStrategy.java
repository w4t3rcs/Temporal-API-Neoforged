package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.annotation.DefinedGlobalLootModifier;
import com.temporal.api.core.event.data.modifier.ApiGlobalLootModifierProvider;
import com.temporal.api.core.event.data.modifier.ChestModifierDescription;

import java.lang.reflect.Constructor;

public class DefinedGlobalLootModifierStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(DefinedGlobalLootModifier.class)) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object globalLootModifier = constructor.newInstance();
            if (globalLootModifier instanceof ChestModifierDescription description) {
                ApiGlobalLootModifierProvider.CHEST_MODIFIER_DESCRIPTIONS.add(description);
            }
        }
    }
}
