package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StairsBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        provider.familyMemberWithItem((StairBlock) blockRegistry.get(), "_stairs",
                provider::stairsBlock,
                (block, parentTexture) -> provider.itemModels()
                        .stairs(provider.getBlockPath(block), parentTexture, parentTexture, parentTexture));
    }
}
