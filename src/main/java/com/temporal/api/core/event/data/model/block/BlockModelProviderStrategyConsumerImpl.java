package com.temporal.api.core.event.data.model.block;

import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer.*;

public class BlockModelProviderStrategyConsumerImpl implements BlockModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull ApiBlockModelProvider provider) {
        CUBED_BLOCKS.forEach(registerBlockModel(provider, CubedBlockModelProviderStrategy::new));
        CUTOUT_CUBED_BLOCKS.forEach(registerBlockModel(provider, CutoutCubedBlockModelProviderStrategy::new));
        CROSS_BLOCKS.forEach(registerBlockModel(provider, CrossBlockModelProviderStrategy::new));
        FLOWER_BLOCKS.forEach(registerBlockModel(provider, FlowerBlockModelProviderStrategy::new));
        LOGS.forEach(registerBlockModel(provider, LogBlockModelProviderStrategy::new));
        BUTTONS.forEach(registerBlockModel(provider, ButtonBlockModelProviderStrategy::new));
        DOORS.forEach(registerBlockModel(provider, DoorBlockModelProviderStrategy::new));
        FENCES.forEach(registerBlockModel(provider, FenceBlockModelProviderStrategy::new));
        FENCE_GATES.forEach(registerBlockModel(provider, FenceGateBlockModelProviderStrategy::new));
        PRESSURE_PLATES.forEach(registerBlockModel(provider, PressurePlateBlockModelProviderStrategy::new));
        SLABS.forEach(registerBlockModel(provider, SlabBlockModelProviderStrategy::new));
        STAIRS.forEach(registerBlockModel(provider, StairsBlockModelProviderStrategy::new));
        TRAPDOORS.forEach(registerBlockModel(provider, TrapDoorBlockModelProviderStrategy::new));
        WALLS.forEach(registerBlockModel(provider, WallBlockModelProviderStrategy::new));
        CUSTOM_MODELS.forEach((key, value) -> value.registerBlockModel(key, provider));
    }

    @Override
    public Consumer<DeferredBlock<?>> registerBlockModel(@NotNull ApiBlockModelProvider provider, @NotNull Supplier<BlockModelProviderStrategy> blockModelProviderStrategy) {
        return block -> blockModelProviderStrategy.get().registerBlockModel(block, provider);
    }
}
