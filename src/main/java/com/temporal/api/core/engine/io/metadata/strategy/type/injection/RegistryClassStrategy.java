package com.temporal.api.core.engine.io.metadata.strategy.type.injection;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.annotation.injection.Registry;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.registry.factory.common.ObjectFactory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

import java.lang.reflect.Field;

public class RegistryClassStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(Registry.class)) {
            Registry registry = clazz.getDeclaredAnnotation(Registry.class);
            String modCondition = registry.mandatoryMod();
            if (modCondition.isBlank() || ModList.get().isLoaded(modCondition)) {
                Field field = clazz.getDeclaredFields()[0];
                field.setAccessible(true);
                ObjectFactory<?> objectFactory = (ObjectFactory<?>) field.get(object);
                IEventBus eventBus = InjectionContext.getFromInstance(IEventBus.class);
                objectFactory.register(eventBus);
            }
        }
    }
}