package com.temporal.api.core.event.data.model.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        DoorBlock block = (DoorBlock) blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.doorBlockWithRenderType(block, ResourceLocation.parse(blockPath + "_bottom"), ResourceLocation.parse(blockPath + "_top"), "minecraft:cutout");
        provider.simpleBlockItem(block, provider.singleTextureItemModel(block));
    }
}
