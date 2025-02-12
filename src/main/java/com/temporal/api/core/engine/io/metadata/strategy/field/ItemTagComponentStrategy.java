package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.ItemTagComponent;
import com.temporal.api.core.event.data.tag.item.ApiItemTagsProvider;
import com.temporal.api.core.event.data.tag.item.ItemTagGenerationPreparer;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ItemTagComponentStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItemTagComponent.class)) {
            field.setAccessible(true);
            ItemTagComponent annotation = field.getDeclaredAnnotation(ItemTagComponent.class);
            DeferredItem<?> deferredItem = (DeferredItem<?>) field.get(null);
            if (!annotation.tagContainer().equals(Object.class)) ItemTagGenerationPreparer.TAG_CONTAINERS.add(annotation.tagContainer());
            boolean exists = ApiItemTagsProvider.TAG_GENERATION_DESCRIPTIONS.containsKey(annotation.tag());
            if (exists) {
                ApiItemTagsProvider.TAG_GENERATION_DESCRIPTIONS.get(annotation.tag())
                        .add(deferredItem);
            } else {
                ArrayList<DeferredItem<?>> items = new ArrayList<>();
                items.add(deferredItem);
                ApiItemTagsProvider.TAG_GENERATION_DESCRIPTIONS.put(annotation.tag(), items);
            }
        }
    }
}
