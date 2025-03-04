package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
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
        POTTED_CROSS_BLOCKS.forEach(registerBlockModel(blockModels, PottedCrossBlockModelProviderStrategy::new));
        BUTTONS.forEach(registerBlockModel(blockModels, ButtonBlockModelProviderStrategy::new));
        DOORS.forEach(registerBlockModel(blockModels, DoorBlockModelProviderStrategy::new));
        FENCES.forEach(registerBlockModel(blockModels, FenceBlockModelProviderStrategy::new));
        FENCE_GATES.forEach(registerBlockModel(blockModels, FenceGateBlockModelProviderStrategy::new));
        PRESSURE_PLATES.forEach(registerBlockModel(blockModels, PressurePlateBlockModelProviderStrategy::new));
        SLABS.forEach(registerBlockModel(blockModels, SlabBlockModelProviderStrategy::new));
        STAIRS.forEach(registerBlockModel(blockModels, StairsBlockModelProviderStrategy::new));
        TRAPDOORS.forEach(registerBlockModel(blockModels, TrapDoorBlockModelProviderStrategy::new));
        WALLS.forEach(registerBlockModel(blockModels, WallBlockModelProviderStrategy::new));
        LOGS.forEach(registerBlockModel(blockModels, LogBlockModelProviderStrategy::new));
        ROTATED_PILLARS.forEach(registerBlockModel(blockModels, RotatedPillarBlockModelProviderStrategy::new));
        CROP_BLOCKS.forEach(registerBlockModel(blockModels, CropBlockModelProviderStrategy::new));
        VINE_BLOCKS.forEach(registerBlockModel(blockModels, VineBlockModelProviderStrategy::new));
        CUSTOM_MODELS.forEach((key, value) -> {
            ((BlockModelProviderStrategy<Block>) value).registerBlockModel((DeferredBlock<Block>) key, blockModels);
        });
    }

    @Override
    public <T extends Block> Consumer<? super DeferredBlock<T>> registerBlockModel(BlockModelGenerators blockModels, @NotNull Supplier<BlockModelProviderStrategy<T>> blockModelProviderStrategy) {
        return (block) -> blockModelProviderStrategy.get()
                .registerBlockModel(block, blockModels);
    }
}
