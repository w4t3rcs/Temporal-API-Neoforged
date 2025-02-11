package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.annotation.TagContainer;
import com.temporal.api.core.event.data.tag.biome.BiomeTagGenerationPreparer;
import com.temporal.api.core.event.data.tag.block.BlockTagGenerationPreparer;
import com.temporal.api.core.event.data.tag.item.ItemTagGenerationPreparer;

public class TagContainerStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(TagContainer.class)) {
            TagContainer annotation = clazz.getDeclaredAnnotation(TagContainer.class);
            switch (annotation.value()) {
                case ITEM -> ItemTagGenerationPreparer.TAG_CONTAINERS.add(clazz);
                case BLOCK -> BlockTagGenerationPreparer.TAG_CONTAINERS.add(clazz);
                case BIOME -> BiomeTagGenerationPreparer.TAG_CONTAINERS.add(clazz);
            }
        }
    }
}
