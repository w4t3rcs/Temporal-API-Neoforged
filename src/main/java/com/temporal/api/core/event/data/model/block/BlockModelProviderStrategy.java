package com.temporal.api.core.event.data.model.block;

import net.neoforged.neoforge.registries.DeferredBlock;

public interface BlockModelProviderStrategy {
    void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData);
}
