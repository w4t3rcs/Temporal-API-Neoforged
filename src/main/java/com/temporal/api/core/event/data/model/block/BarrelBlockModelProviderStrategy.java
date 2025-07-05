package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class BarrelBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlock(block, provider.models()
                .cubeBottomTop(blockPath, ResourceUtils.parse(blockPath + "_side"), ResourceUtils.parse(blockPath + "_bottom"), ResourceUtils.parse(blockPath + "_top")));
        provider.simpleBlockItem(block, provider.models()
                .cubeColumn(blockPath, ResourceUtils.parse(blockPath), ResourceUtils.parse(blockPath)));
    }
}
