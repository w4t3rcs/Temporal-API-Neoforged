package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.ItemModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;

public class ItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItemModel.class)) {
            field.setAccessible(true);
            DeferredItem<?> registryObject = (DeferredItem<?>) field.get(object);
            ItemModel itemModel = field.getDeclaredAnnotation(ItemModel.class);
            switch (itemModel.value()) {
                case BASIC -> ItemModelDescriptionContainer.BASIC_ITEMS.add(registryObject);
                case HANDHELD -> ItemModelDescriptionContainer.HANDHELD_ITEMS.add(registryObject);
                case TRIMMED_ARMOR -> ItemModelDescriptionContainer.TRIMMED_ARMOR_ITEMS.add(registryObject);
                case POTION -> ItemModelDescriptionContainer.POTION_ITEMS.add(registryObject);
            }
        }
    }
}
