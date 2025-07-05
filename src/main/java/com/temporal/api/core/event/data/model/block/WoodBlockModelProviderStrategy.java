package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WoodBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        RotatedPillarBlock block = ((RotatedPillarBlock) blockRegistry.get());
        String logBlockPath = provider.getBlockPath(block).replace("wood", "log");
        provider.axisBlock(block, ResourceUtils.parse(logBlockPath), ResourceUtils.parse(logBlockPath));
        provider.simpleBlockItem(block, provider.models()
                .cubeColumn(logBlockPath, ResourceUtils.parse(logBlockPath), ResourceUtils.parse(logBlockPath)));
    }
}
