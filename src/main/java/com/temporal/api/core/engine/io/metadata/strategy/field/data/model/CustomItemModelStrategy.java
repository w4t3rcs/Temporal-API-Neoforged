package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.CustomItemModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer;
import com.temporal.api.core.event.data.model.item.ItemModelProviderStrategy;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;

public class CustomItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CustomItemModel.class)) {
            field.setAccessible(true);
            DeferredItem<?> registryObject = (DeferredItem<?>) field.get(object);
            CustomItemModel blockModel = field.getDeclaredAnnotation(CustomItemModel.class);
            ItemModelProviderStrategy providerStrategy = blockModel.value()
                    .getDeclaredConstructor()
                    .newInstance();
            ItemModelDescriptionContainer.CUSTOM_MODELS.put(registryObject, providerStrategy);
        }
    }
}
