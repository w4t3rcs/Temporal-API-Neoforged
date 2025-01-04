package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface BlockModelProviderStrategyConsumer {
    void registerModels(@NotNull BlockModelGenerators blockModels);

    <T extends Block> Consumer<? super DeferredBlock<T>> registerBlockModel(@NotNull BlockModelProviderStrategy<T> blockModelProviderStrategy);
}
