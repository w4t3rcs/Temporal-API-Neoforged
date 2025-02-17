package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.annotation.TagContainer;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import com.temporal.api.core.event.data.preparer.tag.block.BlockTagDynamicPreparer;
import com.temporal.api.core.event.data.preparer.tag.item.ItemTagDynamicPreparer;

public class TagContainerStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(TagContainer.class)) {
            TagContainer annotation = clazz.getDeclaredAnnotation(TagContainer.class);
            switch (annotation.value()) {
                case ITEM -> ItemTagDynamicPreparer.TAG_CONTAINERS.add(clazz);
                case BLOCK -> BlockTagDynamicPreparer.TAG_CONTAINERS.add(clazz);
                case BIOME -> BiomeTagDynamicPreparer.TAG_CONTAINERS.add(clazz);
            }
        }
    }
}
