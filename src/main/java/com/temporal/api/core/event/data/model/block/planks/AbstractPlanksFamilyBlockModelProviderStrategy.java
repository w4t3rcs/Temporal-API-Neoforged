package com.temporal.api.core.event.data.model.block.planks;

import com.temporal.api.core.event.data.model.block.AbstractFamilyBlockModelProviderStrategy;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class AbstractPlanksFamilyBlockModelProviderStrategy extends AbstractFamilyBlockModelProviderStrategy {
    @Override
    protected BlockModelGenerators.BlockFamilyProvider createFamilyProvider(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels, String target) {
        return super.createFamilyProvider(blockRegistry, blockModels, target, "_planks");
    }
}
