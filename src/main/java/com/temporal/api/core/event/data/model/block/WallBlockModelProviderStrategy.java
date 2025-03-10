package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WallBlockModelProviderStrategy extends AbstractFamilyBlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        createFamilyProvider(blockRegistry, blockModels, "_wall").wall(blockRegistry.get());
    }
}
