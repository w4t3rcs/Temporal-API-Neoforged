package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.FenceGateBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceGateBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        provider.familyMemberWithItem((FenceGateBlock) blockRegistry.get(), "_fence_gate",
                (block, parentTexture) -> provider.fenceGateBlockWithRenderType(block, parentTexture, "minecraft:cutout"),
                (block, parentTexture) -> provider.itemModels()
                        .fenceGate(provider.getBlockPath(block), parentTexture));
    }
}
