package com.temporal.api.core.event.data.model.block;

import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class AbstractModelProviderStrategy implements BlockModelProviderStrategy  {
    protected String getBlockPath(DeferredBlock<?> blockRegistry) {
        String path = blockRegistry.getId().getPath();
        return "block/" + path;
    }
}
