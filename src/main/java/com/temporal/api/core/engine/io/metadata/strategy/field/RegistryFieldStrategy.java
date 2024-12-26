package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.Registry;
import com.temporal.api.core.registry.factory.common.ObjectFactory;
import net.minecraftforge.fml.ModList;

import java.lang.reflect.Field;

public class RegistryFieldStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Registry.class)) {
            field.setAccessible(true);
            Registry registry = field.getDeclaredAnnotation(Registry.class);
            String modCondition = registry.registryOnModCondition();
            if (modCondition.isBlank() || ModList.get().isLoaded(modCondition)) {
                ObjectFactory<?> objectFactory = (ObjectFactory<?>) field.get(object);
                objectFactory.register();
            }
        }
    }
}
