package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PressurePlateBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        provider.familyMemberWithItem((PressurePlateBlock) blockRegistry.get(), "_pressure_plate",
                provider::pressurePlateBlock,
                (block, parentTexture) -> provider.itemModels()
                        .pressurePlate(provider.getBlockPath(block), parentTexture));
    }
}
