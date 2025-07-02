package com.temporal.api.core.event.data.model.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LogBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        RotatedPillarBlock block = ((RotatedPillarBlock) blockRegistry.get());
        provider.logBlock(block);
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlockItem(block, provider.models()
                .cubeColumn(blockPath, ResourceLocation.parse(blockPath + "_top"), ResourceLocation.parse(blockPath)));
    }
}
