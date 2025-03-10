package com.temporal.api.core.event.data.model.block.planks;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PlanksSlabBlockModelProviderStrategy extends AbstractPlanksFamilyBlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        createFamilyProvider(blockRegistry, blockModels, "_slab").slab(blockRegistry.get());
    }
}
