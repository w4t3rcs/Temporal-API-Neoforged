package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.collection.SimplePair;
import com.temporal.api.core.engine.io.metadata.annotation.data.model.CustomItemModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer;
import com.temporal.api.core.event.data.model.item.ItemModelProviderStrategy;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CustomItemModel.class)) {
            field.setAccessible(true);
            DeferredItem<?> registryObject = (DeferredItem<?>) field.get(object);
            CustomItemModel itemModel = field.getDeclaredAnnotation(CustomItemModel.class);
            String[] additionalStrings = itemModel.additionalStrings();
            Integer[] additionalInts = Arrays.stream(itemModel.additionalInts()).boxed().toArray(Integer[]::new);
            Double[] additionalDoubles = Arrays.stream(itemModel.additionalDoubles()).boxed().toArray(Double[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts, additionalDoubles);
            ItemModelProviderStrategy providerStrategy = itemModel.value()
                    .getDeclaredConstructor()
                    .newInstance();
            ItemModelDescriptionContainer.CUSTOM_MODELS.put(new SimplePair<>(registryObject, additionalData), providerStrategy);
        }
    }
}
