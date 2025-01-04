package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.temporal.api.core.event.data.model.block.BlockModelDescriptionContainer.*;

public class BlockModelProviderStrategyConsumerImpl implements BlockModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull BlockModelGenerators blockModels) {
        CUBED_BLOCKS.forEach(registerBlockModel(new CubedBlockModelProviderStrategy(blockModels)));
        BUTTONS.forEach(registerBlockModel(new ButtonBlockModelProviderStrategy(blockModels)));
        DOORS.forEach(registerBlockModel(new DoorBlockModelProviderStrategy(blockModels)));
        FENCES.forEach(registerBlockModel(new FenceBlockModelProviderStrategy(blockModels)));
        FENCE_GATES.forEach(registerBlockModel(new FenceGateBlockModelProviderStrategy(blockModels)));
        PRESSURE_PLATES.forEach(registerBlockModel(new PressurePlateBlockModelProviderStrategy(blockModels)));
        SLABS.forEach(registerBlockModel(new SlabBlockModelProviderStrategy(blockModels)));
        STAIRS.forEach(registerBlockModel(new StairsBlockModelProviderStrategy(blockModels)));
        TRAPDOORS.forEach(registerBlockModel(new TrapDoorBlockModelProviderStrategy(blockModels)));
        WALLS.forEach(registerBlockModel(new WallBlockModelProviderStrategy(blockModels)));
        LOGS.forEach(registerBlockModel(new LogBlockModelProviderStrategy(blockModels)));
        ROTATED_PILLARS.forEach(registerBlockModel(new RotatedPillarBlockModelProviderStrategy(blockModels)));
    }

    @Override
    public <T extends Block> Consumer<? super DeferredBlock<T>> registerBlockModel(@NotNull BlockModelProviderStrategy<T> blockModelProviderStrategy) {
        return blockModelProviderStrategy::registerBlockModel;
    }
}
