package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CubedBlockModelProviderStrategy extends AbstractModelProviderStrategy<Block> {
    @Override
    public void registerBlockModel(DeferredBlock<Block> blockRegistry, BlockModelGenerators blockModels) {
        final Block block = blockRegistry.get();
        blockModels.createTrivialCube(block);
    }
}
