package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class RailBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlock(block, provider.models()
                .withExistingParent(blockPath, "block/rail_flat")
                .texture("rail", blockPath));
        provider.singleTextureItemModel(block);
    }
}
