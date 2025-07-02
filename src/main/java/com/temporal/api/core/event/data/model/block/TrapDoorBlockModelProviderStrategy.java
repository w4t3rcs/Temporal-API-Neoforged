package com.temporal.api.core.event.data.model.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TrapDoorBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        TrapDoorBlock block = (TrapDoorBlock) blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        ResourceLocation texture = ResourceLocation.parse(blockPath);
        provider.trapdoorBlockWithRenderType(block, texture, true, "minecraft:cutout");
        provider.simpleBlockItem(block, provider.itemModels().trapdoorOrientableBottom(blockPath, texture));
    }
}
