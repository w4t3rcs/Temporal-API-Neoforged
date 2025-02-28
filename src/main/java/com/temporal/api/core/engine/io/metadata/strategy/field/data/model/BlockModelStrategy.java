package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.metadata.annotation.data.model.BlockModel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class BlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockModel.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            BlockModel blockModel = field.getDeclaredAnnotation(BlockModel.class);
            switch (blockModel.type()) {
                case CUBED -> BlockModelDescriptionContainer.CUBED_BLOCKS.add((DeferredBlock<Block>) registryObject);
                case CROSS -> BlockModelDescriptionContainer.CROSS_BLOCKS.add((DeferredBlock<Block>) registryObject);
                case TINTED_CROSS -> BlockModelDescriptionContainer.TINTED_CROSS_BLOCKS.add((DeferredBlock<Block>) registryObject);
                case DOOR -> BlockModelDescriptionContainer.DOORS.add((DeferredBlock<DoorBlock>) registryObject);
                case BUTTON -> BlockModelDescriptionContainer.BUTTONS.add((DeferredBlock<ButtonBlock>) registryObject);
                case FENCE -> BlockModelDescriptionContainer.FENCES.add((DeferredBlock<FenceBlock>) registryObject);
                case FENCE_GATE -> BlockModelDescriptionContainer.FENCE_GATES.add((DeferredBlock<FenceGateBlock>) registryObject);
                case PRESSURE_PLATE -> BlockModelDescriptionContainer.PRESSURE_PLATES.add((DeferredBlock<PressurePlateBlock>) registryObject);
                case TRAPDOOR -> BlockModelDescriptionContainer.TRAPDOORS.add((DeferredBlock<TrapDoorBlock>) registryObject);
                case SLAB -> BlockModelDescriptionContainer.SLABS.add((DeferredBlock<SlabBlock>) registryObject);
                case STAIRS -> BlockModelDescriptionContainer.STAIRS.add((DeferredBlock<StairBlock>) registryObject);
                case WALL -> BlockModelDescriptionContainer.WALLS.add((DeferredBlock<WallBlock>) registryObject);
                case LOG -> BlockModelDescriptionContainer.LOGS.add((DeferredBlock<LogBlock>) registryObject);
                case ROTATED_PILLAR -> BlockModelDescriptionContainer.ROTATED_PILLARS.add((DeferredBlock<RotatedPillarBlock>) registryObject);
                case CROP -> BlockModelDescriptionContainer.CROP_BLOCKS.add((DeferredBlock<CropBlock>) registryObject);
                case VINE -> BlockModelDescriptionContainer.VINE_BLOCKS.add((DeferredBlock<VineBlock>) registryObject);
            }
        }
    }
}
