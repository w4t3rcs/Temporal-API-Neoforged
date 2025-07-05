package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SlabBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        provider.familyMemberWithItem((SlabBlock) blockRegistry.get(), "_slab",
                (block, parentTexture) -> provider.slabBlock(block, parentTexture, parentTexture) ,
                (block, parentTexture) -> provider.itemModels()
                        .slab(provider.getBlockPath(block), parentTexture, parentTexture, parentTexture));
    }
}
