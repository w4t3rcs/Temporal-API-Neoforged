package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface BlockModelProviderStrategy {
    void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels);
}
