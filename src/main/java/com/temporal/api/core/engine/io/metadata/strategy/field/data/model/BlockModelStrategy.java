package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.BlockModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class BlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockModel.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            BlockModel blockModel = field.getDeclaredAnnotation(BlockModel.class);
            switch (blockModel.type()) {
                case CUBED -> BlockModelDescriptionContainer.CUBED_BLOCKS.add(registryObject);
                case CUTOUT_CUBED -> BlockModelDescriptionContainer.CUTOUT_CUBED_BLOCKS.add(registryObject);
                case CROSS -> BlockModelDescriptionContainer.CROSS_BLOCKS.add(registryObject);
                case POTTED_CROSS -> BlockModelDescriptionContainer.POTTED_CROSS_BLOCKS.add(registryObject);
                case DOOR -> BlockModelDescriptionContainer.DOORS.add(registryObject);
                case BUTTON -> BlockModelDescriptionContainer.BUTTONS.add(registryObject);
                case FENCE -> BlockModelDescriptionContainer.FENCES.add(registryObject);
                case FENCE_GATE -> BlockModelDescriptionContainer.FENCE_GATES.add(registryObject);
                case PRESSURE_PLATE -> BlockModelDescriptionContainer.PRESSURE_PLATES.add(registryObject);
                case TRAPDOOR -> BlockModelDescriptionContainer.TRAPDOORS.add(registryObject);
                case SLAB -> BlockModelDescriptionContainer.SLABS.add(registryObject);
                case STAIRS -> BlockModelDescriptionContainer.STAIRS.add(registryObject);
                case WALL -> BlockModelDescriptionContainer.WALLS.add(registryObject);
                case LOG -> BlockModelDescriptionContainer.LOGS.add(registryObject);
                case ROTATED_PILLAR -> BlockModelDescriptionContainer.ROTATED_PILLARS.add(registryObject);
                case CROP -> BlockModelDescriptionContainer.CROP_BLOCKS.add(registryObject);
                case VINE -> BlockModelDescriptionContainer.VINE_BLOCKS.add(registryObject);
            }
        }
    }
}
