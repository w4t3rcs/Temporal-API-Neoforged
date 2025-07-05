package com.temporal.api.core.event.data.model.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class VineBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlock(block, provider.models()
                .withExistingParent(blockPath, "block/vine")
                .texture("vine", blockPath)
                .texture("particle", blockPath));
        provider.singleTextureItemModel(block);
    }
}
