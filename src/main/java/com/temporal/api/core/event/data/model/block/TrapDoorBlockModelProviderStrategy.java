package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TrapDoorBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        TrapDoorBlock block = (TrapDoorBlock) blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        ResourceLocation texture = ResourceUtils.parse(blockPath);
        provider.trapdoorBlockWithRenderType(block, texture, true, "minecraft:cutout");
        provider.simpleBlockItem(block, provider.itemModels().trapdoorOrientableBottom(blockPath, texture));
    }
}
