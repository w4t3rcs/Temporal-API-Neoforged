package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.CustomBlockModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer;
import com.temporal.api.core.event.data.model.block.BlockModelProviderStrategy;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class CustomBlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CustomBlockModel.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            CustomBlockModel blockModel = field.getDeclaredAnnotation(CustomBlockModel.class);
            BlockModelProviderStrategy providerStrategy = blockModel.value()
                    .getDeclaredConstructor()
                    .newInstance();
            BlockModelDescriptionContainer.CUSTOM_MODELS.put(registryObject, providerStrategy);
        }
    }
}
