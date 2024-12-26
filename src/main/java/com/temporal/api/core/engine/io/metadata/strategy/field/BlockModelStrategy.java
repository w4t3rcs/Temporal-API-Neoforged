package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.metadata.annotation.BlockModel;
import com.temporal.api.core.event.data.model.block.BlockStateProviderImpl;
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
                case CUBED -> BlockStateProviderImpl.BLOCKS.add((DeferredBlock<Block>) registryObject);
                case DOOR -> BlockStateProviderImpl.DOORS.add((DeferredBlock<DoorBlock>) registryObject);
                case BUTTON -> BlockStateProviderImpl.BUTTONS.add((DeferredBlock<ButtonBlock>) registryObject);
                case FENCE -> BlockStateProviderImpl.FENCES.add((DeferredBlock<FenceBlock>) registryObject);
                case FENCE_GATE -> BlockStateProviderImpl.FENCE_GATES.add((DeferredBlock<FenceGateBlock>) registryObject);
                case PRESSURE_PLATE -> BlockStateProviderImpl.PRESSURE_PLATES.add((DeferredBlock<PressurePlateBlock>) registryObject);
                case TRAPDOOR -> BlockStateProviderImpl.TRAPDOORS.add((DeferredBlock<TrapDoorBlock>) registryObject);
                case SLAB -> BlockStateProviderImpl.SLABS.add((DeferredBlock<SlabBlock>) registryObject);
                case STAIRS -> BlockStateProviderImpl.STAIRS.add((DeferredBlock<StairBlock>) registryObject);
                case WALL -> BlockStateProviderImpl.WALLS.add((DeferredBlock<WallBlock>) registryObject);
                case LOG -> BlockStateProviderImpl.LOGS.add((DeferredBlock<LogBlock>) registryObject);
                case ROTATED_PILLAR -> BlockStateProviderImpl.ROTATED_PILLARS.add((DeferredBlock<RotatedPillarBlock>) registryObject);
            }
        }
    }
}
