package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.ItemModel;
import com.temporal.api.core.event.data.model.item.ItemModelProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class ItemModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItemModel.class)) {
            field.setAccessible(true);
            RegistryObject<Item> registryObject = (RegistryObject<Item>) field.get(object);
            ItemModel itemModel = field.getDeclaredAnnotation(ItemModel.class);
            switch (itemModel.type()) {
                case SIMPLE -> ItemModelProvider.ITEMS.add(registryObject);
                case HANDHELD -> ItemModelProvider.HANDHELD_ITEMS.add(registryObject);
            }
        }
    }
}
