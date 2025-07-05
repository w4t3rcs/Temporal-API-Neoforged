package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.engine.io.metadata.annotation.data.other.BlockLootTable;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.loot.BlockLootTableProvider;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;
import java.util.Arrays;

public class BlockLootTableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockLootTable.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            BlockLootTable blockLootTable = field.getDeclaredAnnotation(BlockLootTable.class);
            String[] additionalStrings = blockLootTable.additionalStrings();
            Integer[] additionalInts = Arrays.stream(blockLootTable.additionalInts()).boxed().toArray(Integer[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts);
            switch (blockLootTable.value()) {
                case SELF -> BlockLootTableProvider.SELF.put(registryObject, additionalData);
                case SILK_TOUCH -> BlockLootTableProvider.SILK_TOUCH.put(registryObject, additionalData);
                case POTTED_CONTENT -> BlockLootTableProvider.POTTED_CONTENT.put(registryObject, additionalData);
                case SIGN -> BlockLootTableProvider.SIGN.put(registryObject, additionalData);
                case HANGING_SIGN -> BlockLootTableProvider.HANGING_SIGN.put(registryObject, additionalData);
                case OTHER -> BlockLootTableProvider.OTHER.put(registryObject, additionalData);
            }
        }
    }
}
