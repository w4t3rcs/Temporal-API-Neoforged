package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class AbstractModelProviderStrategy<T extends Block> implements BlockModelProviderStrategy<T>  {
    private final BlockModelGenerators blockModels;

    public AbstractModelProviderStrategy(BlockModelGenerators blockModels) {
        this.blockModels = blockModels;
    }

    protected String getBlockPath(DeferredBlock<T> blockRegistry) {
        String path = blockRegistry.getId().getPath();
        return "block/" + path;
    }

    public BlockModelGenerators getBlockModels() {
        return blockModels;
    }
}
