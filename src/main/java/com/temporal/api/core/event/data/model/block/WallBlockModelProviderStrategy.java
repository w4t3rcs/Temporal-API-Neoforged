package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WallBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        provider.familyMemberWithItem((WallBlock) blockRegistry.get(), "_wall",
                (block, parentTexture) -> provider.wallBlockWithRenderType(block, parentTexture, "minecraft:cutout"),
                (block, parentTexture) -> provider.itemModels()
                        .wallInventory(provider.getBlockPath(block), parentTexture));
    }
}
