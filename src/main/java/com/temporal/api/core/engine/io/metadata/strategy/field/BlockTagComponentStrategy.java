package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.BlockTagComponent;
import com.temporal.api.core.event.data.tag.block.ApiBlockTagsProvider;
import com.temporal.api.core.event.data.tag.block.BlockTagGenerationDescription;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BlockTagComponentStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockTagComponent.class)) {
            field.setAccessible(true);
            BlockTagComponent annotation = field.getDeclaredAnnotation(BlockTagComponent.class);
            DeferredBlock<?> deferredBlock = (DeferredBlock<?>) field.get(null);
            boolean exists = ApiBlockTagsProvider.TAG_GENERATION_DESCRIPTIONS.containsKey(annotation.tag());
            if (exists) {
                BlockTagGenerationDescription description = ApiBlockTagsProvider.TAG_GENERATION_DESCRIPTIONS.get(annotation.tag());
                description.blocks().add(deferredBlock);
                ApiBlockTagsProvider.TAG_GENERATION_DESCRIPTIONS.put(annotation.tag(), description);
            } else {
                ArrayList<DeferredBlock<?>> blocks = new ArrayList<>();
                blocks.add(deferredBlock);
                BlockTagGenerationDescription description = new BlockTagGenerationDescription(blocks, annotation.tagContainer());
                ApiBlockTagsProvider.TAG_GENERATION_DESCRIPTIONS.put(annotation.tag(), description);
            }
        }
    }
}
