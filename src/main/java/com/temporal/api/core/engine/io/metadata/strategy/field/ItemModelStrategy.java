package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.ItemModel;
import com.temporal.api.core.event.data.model.item.ItemModelProviderImpl;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class ItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItemModel.class)) {
            field.setAccessible(true);
            DeferredItem<Item> registryObject = (DeferredItem<Item>) field.get(object);
            ItemModel itemModel = field.getDeclaredAnnotation(ItemModel.class);
            switch (itemModel.type()) {
                case SIMPLE -> ItemModelProviderImpl.ITEMS.add(registryObject);
                case HANDHELD -> ItemModelProviderImpl.HANDHELD_ITEMS.add(registryObject);
            }
        }
    }
}
