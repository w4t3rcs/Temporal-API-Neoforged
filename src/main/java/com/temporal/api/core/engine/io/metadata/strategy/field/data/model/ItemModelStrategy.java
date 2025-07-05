package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.ItemModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItemModel.class)) {
            field.setAccessible(true);
            DeferredItem<?> registryObject = (DeferredItem<?>) field.get(object);
            ItemModel itemModel = field.getDeclaredAnnotation(ItemModel.class);
            String[] additionalStrings = itemModel.additionalStrings();
            Integer[] additionalInts = Arrays.stream(itemModel.additionalInts()).boxed().toArray(Integer[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts);
            switch (itemModel.value()) {
                case BASIC -> ItemModelDescriptionContainer.BASIC_ITEMS.put(registryObject, additionalData);
                case HANDHELD -> ItemModelDescriptionContainer.HANDHELD_ITEMS.put(registryObject, additionalData);
                case BOW -> ItemModelDescriptionContainer.BOW_ITEMS.put(registryObject, additionalData);
                case CROSSBOW -> ItemModelDescriptionContainer.CROSSBOW_ITEMS.put(registryObject, additionalData);
                case TRIMMED_ARMOR -> ItemModelDescriptionContainer.TRIMMED_ARMOR_ITEMS.put(registryObject, additionalData);
                case POTION -> ItemModelDescriptionContainer.POTION_ITEMS.put(registryObject, additionalData);
            }
        }
    }
}
