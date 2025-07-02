package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ButtonBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        provider.familyMemberWithItem((ButtonBlock) blockRegistry.get(), "_button",
                provider::buttonBlock,
                (block, parentTexture) -> provider.itemModels()
                        .buttonInventory(provider.getBlockPath(block), parentTexture));
    }
}
