package com.temporal.api.core.event.data.model.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CrossBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        Block block = blockRegistry.get();
        provider.simpleBlock(block, provider.models()
                .cross(BuiltInRegistries.BLOCK.getKey(block)
                        .getPath(), provider.blockTexture(block))
                .renderType("minecraft:cutout"));
        provider.singleTextureItemModel(block);
    }
}
