package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.collection.SimplePair;
import com.temporal.api.core.engine.io.metadata.annotation.data.model.CustomBlockModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer;
import com.temporal.api.core.event.data.model.block.BlockModelProviderStrategy;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomBlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CustomBlockModel.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            CustomBlockModel blockModel = field.getDeclaredAnnotation(CustomBlockModel.class);
            String[] additionalStrings = blockModel.additionalStrings();
            Integer[] additionalInts = Arrays.stream(blockModel.additionalInts()).boxed().toArray(Integer[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts);
            BlockModelProviderStrategy providerStrategy = blockModel.value()
                    .getDeclaredConstructor()
                    .newInstance();
            BlockModelDescriptionContainer.CUSTOM_MODELS.put(new SimplePair<>(registryObject, additionalData), providerStrategy);
        }
    }
}
