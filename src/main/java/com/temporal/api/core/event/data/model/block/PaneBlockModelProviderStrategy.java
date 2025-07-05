package com.temporal.api.core.event.data.model.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PaneBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlock(block, provider.models()
                .paneNoSide(blockPath, ResourceLocation.parse(blockPath))
                .renderType("minecraft:cutout"));
        provider.simpleBlock(block, provider.models()
                .paneNoSideAlt(blockPath, ResourceLocation.parse(blockPath))
                .renderType("minecraft:cutout"));
        provider.simpleBlock(block, provider.models()
                .panePost(blockPath, ResourceLocation.parse(blockPath), ResourceLocation.parse(blockPath + "_top"))
                .renderType("minecraft:cutout"));
        provider.simpleBlock(block, provider.models()
                .paneSide(blockPath, ResourceLocation.parse(blockPath), ResourceLocation.parse(blockPath + "_top"))
                .renderType("minecraft:cutout"));
        provider.simpleBlock(block, provider.models()
                .paneSideAlt(blockPath, ResourceLocation.parse(blockPath), ResourceLocation.parse(blockPath + "_top"))
                .renderType("minecraft:cutout"));
        provider.singleTextureItemModel(block);
    }
}
