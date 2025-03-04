package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface BlockModelProviderStrategy<T extends Block> {
    void registerBlockModel(DeferredBlock<T> blockRegistry, BlockModelGenerators blockModels);
}
