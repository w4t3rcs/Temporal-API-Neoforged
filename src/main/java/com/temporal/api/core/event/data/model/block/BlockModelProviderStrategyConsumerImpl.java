package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.event.data.model.block.planks.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer.*;

public class BlockModelProviderStrategyConsumerImpl implements BlockModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull BlockModelGenerators blockModels) {
        CUBED_BLOCKS.forEach(registerBlockModel(blockModels, CubedBlockModelProviderStrategy::new));
        CUTOUT_CUBED_BLOCKS.forEach(registerBlockModel(blockModels, CutoutCubedBlockModelProviderStrategy::new));
        CROSS_BLOCKS.forEach(registerBlockModel(blockModels, CrossBlockModelProviderStrategy::new));
        FLOWER_BLOCKS.forEach(registerBlockModel(blockModels, FlowerBlockModelProviderStrategy::new));
        ROTATED_PILLARS.forEach(registerBlockModel(blockModels, RotatedPillarBlockModelProviderStrategy::new));
        LOGS.forEach(registerBlockModel(blockModels, LogBlockModelProviderStrategy::new));
        WOODS.forEach(registerBlockModel(blockModels, WoodBlockModelProviderStrategy::new));
        BUTTONS.forEach(registerBlockModel(blockModels, ButtonBlockModelProviderStrategy::new));
        DOORS.forEach(registerBlockModel(blockModels, DoorBlockModelProviderStrategy::new));
        FENCES.forEach(registerBlockModel(blockModels, FenceBlockModelProviderStrategy::new));
        FENCE_GATES.forEach(registerBlockModel(blockModels, FenceGateBlockModelProviderStrategy::new));
        PRESSURE_PLATES.forEach(registerBlockModel(blockModels, PressurePlateBlockModelProviderStrategy::new));
        SLABS.forEach(registerBlockModel(blockModels, SlabBlockModelProviderStrategy::new));
        STAIRS.forEach(registerBlockModel(blockModels, StairsBlockModelProviderStrategy::new));
        TRAPDOORS.forEach(registerBlockModel(blockModels, TrapDoorBlockModelProviderStrategy::new));
        WALLS.forEach(registerBlockModel(blockModels, WallBlockModelProviderStrategy::new));
        PLANKS_BUTTONS.forEach(registerBlockModel(blockModels, PlanksButtonBlockModelProviderStrategy::new));
        PLANKS_FENCES.forEach(registerBlockModel(blockModels, PlanksFenceBlockModelProviderStrategy::new));
        PLANKS_FENCE_GATES.forEach(registerBlockModel(blockModels, PlanksFenceGateBlockModelProviderStrategy::new));
        PLANKS_PRESSURE_PLATES.forEach(registerBlockModel(blockModels, PlanksPressurePlateBlockModelProviderStrategy::new));
        PLANKS_SLABS.forEach(registerBlockModel(blockModels, PlanksSlabBlockModelProviderStrategy::new));
        PLANKS_STAIRS.forEach(registerBlockModel(blockModels, PlanksStairsBlockModelProviderStrategy::new));
        CROP_BLOCKS.forEach(registerBlockModel(blockModels, CropBlockModelProviderStrategy::new));
        VINE_BLOCKS.forEach(registerBlockModel(blockModels, VineBlockModelProviderStrategy::new));
        CUSTOM_MODELS.forEach((key, value) -> value.registerBlockModel(key, blockModels));
    }

    @Override
    public Consumer<DeferredBlock<?>> registerBlockModel(@NotNull BlockModelGenerators blockModels, @NotNull Supplier<BlockModelProviderStrategy> blockModelProviderStrategy) {
        return block -> blockModelProviderStrategy.get().registerBlockModel(block, blockModels);
    }
}
