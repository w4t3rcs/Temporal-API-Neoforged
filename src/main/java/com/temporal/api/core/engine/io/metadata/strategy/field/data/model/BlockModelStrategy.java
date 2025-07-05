package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.BlockModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;
import java.util.Arrays;

public class BlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockModel.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            BlockModel blockModel = field.getDeclaredAnnotation(BlockModel.class);
            String[] additionalStrings = blockModel.additionalStrings();
            Integer[] additionalInts = Arrays.stream(blockModel.additionalInts()).boxed().toArray(Integer[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts);
            switch (blockModel.value()) {
                case CUBED -> BlockModelDescriptionContainer.CUBED_BLOCKS.put(registryObject, additionalData);
                case CUTOUT_CUBED -> BlockModelDescriptionContainer.CUTOUT_CUBED_BLOCKS.put(registryObject, additionalData);
                case CROSS -> BlockModelDescriptionContainer.CROSS_BLOCKS.put(registryObject, additionalData);
                case FLOWER -> BlockModelDescriptionContainer.FLOWER_BLOCKS.put(registryObject, additionalData);
                case LOG -> BlockModelDescriptionContainer.LOGS.put(registryObject, additionalData);
                case DOOR -> BlockModelDescriptionContainer.DOORS.put(registryObject, additionalData);
                case BUTTON -> BlockModelDescriptionContainer.BUTTONS.put(registryObject, additionalData);
                case FENCE -> BlockModelDescriptionContainer.FENCES.put(registryObject, additionalData);
                case FENCE_GATE -> BlockModelDescriptionContainer.FENCE_GATES.put(registryObject, additionalData);
                case PRESSURE_PLATE -> BlockModelDescriptionContainer.PRESSURE_PLATES.put(registryObject, additionalData);
                case TRAPDOOR -> BlockModelDescriptionContainer.TRAPDOORS.put(registryObject, additionalData);
                case SLAB -> BlockModelDescriptionContainer.SLABS.put(registryObject, additionalData);
                case STAIRS -> BlockModelDescriptionContainer.STAIRS.put(registryObject, additionalData);
                case WALL -> BlockModelDescriptionContainer.WALLS.put(registryObject, additionalData);
                case SIGN -> BlockModelDescriptionContainer.SIGNS.put(registryObject, additionalData);
                case HANGING_SIGN -> BlockModelDescriptionContainer.HANGING_SIGNS.put(registryObject, additionalData);
            }
        }
    }
}
