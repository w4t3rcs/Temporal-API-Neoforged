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
            Double[] additionalDoubles = Arrays.stream(blockLootTable.additionalDoubles()).boxed().toArray(Double[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts, additionalDoubles);
            switch (blockLootTable.value()) {
                case SELF -> BlockLootTableProvider.SELF.put(registryObject, additionalData);
                case SILK_TOUCH -> BlockLootTableProvider.SILK_TOUCH.put(registryObject, additionalData);
                case POTTED_CONTENT -> BlockLootTableProvider.POTTED_CONTENTS.put(registryObject, additionalData);
                case ORE -> BlockLootTableProvider.ORES.put(registryObject, additionalData);
                case MULTIPLE_ORE -> BlockLootTableProvider.MULTIPLE_ORES.put(registryObject, additionalData);
                case GRASS -> BlockLootTableProvider.GRASSES.put(registryObject, additionalData);
                case LEAVES -> BlockLootTableProvider.LEAVES.put(registryObject, additionalData);
                case SHULKER_BOX -> BlockLootTableProvider.SHULKER_BOXES.put(registryObject, additionalData);
                case BANNER -> BlockLootTableProvider.BANNERS.put(registryObject, additionalData);
                case MUSHROOM_BLOCK -> BlockLootTableProvider.MUSHROOM_BLOCKS.put(registryObject, additionalData);
                case SHEARS_ONLY -> BlockLootTableProvider.SHEARS_ONLY.put(registryObject, additionalData);
                case CROP -> BlockLootTableProvider.CROPS.put(registryObject, additionalData);
                case DOOR -> BlockLootTableProvider.DOORS.put(registryObject, additionalData);
                case OTHER -> BlockLootTableProvider.OTHER.put(registryObject, additionalData);
                case EMPTY -> BlockLootTableProvider.EMPTY.put(registryObject, additionalData);
            }
        }
    }
}
