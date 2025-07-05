package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.FenceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        provider.familyMemberWithItem((FenceBlock) blockRegistry.get(), "_fence",
                (block, parentTexture) -> provider.fenceBlockWithRenderType(block, parentTexture, "minecraft:cutout"),
                (block, parentTexture) -> provider.itemModels()
                        .fenceInventory(provider.getBlockPath(block), parentTexture));
    }
}
