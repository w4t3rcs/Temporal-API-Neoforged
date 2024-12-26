package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.metadata.annotation.BlockModel;
import com.temporal.api.core.event.data.model.block.BlockStateProvider;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class BlockModelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockModel.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            BlockModel blockModel = field.getDeclaredAnnotation(BlockModel.class);
            switch (blockModel.type()) {
                case CUBED -> BlockStateProvider.BLOCKS.add((RegistryObject<Block>) registryObject);
                case DOOR -> BlockStateProvider.DOORS.add((RegistryObject<DoorBlock>) registryObject);
                case BUTTON -> BlockStateProvider.BUTTONS.add((RegistryObject<ButtonBlock>) registryObject);
                case FENCE -> BlockStateProvider.FENCES.add((RegistryObject<FenceBlock>) registryObject);
                case FENCE_GATE -> BlockStateProvider.FENCE_GATES.add((RegistryObject<FenceGateBlock>) registryObject);
                case PRESSURE_PLATE -> BlockStateProvider.PRESSURE_PLATES.add((RegistryObject<PressurePlateBlock>) registryObject);
                case TRAPDOOR -> BlockStateProvider.TRAPDOORS.add((RegistryObject<TrapDoorBlock>) registryObject);
                case SLAB -> BlockStateProvider.SLABS.add((RegistryObject<SlabBlock>) registryObject);
                case STAIRS -> BlockStateProvider.STAIRS.add((RegistryObject<StairBlock>) registryObject);
                case WALL -> BlockStateProvider.WALLS.add((RegistryObject<WallBlock>) registryObject);
                case LOG -> BlockStateProvider.LOGS.add((RegistryObject<LogBlock>) registryObject);
                case ROTATED_PILLAR -> BlockStateProvider.ROTATED_PILLARS.add((RegistryObject<RotatedPillarBlock>) registryObject);
            }
        }
    }
}
