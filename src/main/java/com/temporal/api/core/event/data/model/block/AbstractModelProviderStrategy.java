package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class AbstractModelProviderStrategy<T extends Block> implements BlockModelProviderStrategy<T>  {
    protected String getBlockPath(DeferredBlock<T> blockRegistry) {
        String path = blockRegistry.getId().getPath();
        return "block/" + path;
    }
}
