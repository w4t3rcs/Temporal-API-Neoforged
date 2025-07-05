package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.world.level.block.DoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        DoorBlock block = (DoorBlock) blockRegistry.get();
        String blockPath = provider.getBlockPath(block);
        provider.doorBlockWithRenderType(block, ResourceUtils.parse(blockPath + "_bottom"), ResourceUtils.parse(blockPath + "_top"), "minecraft:cutout");
        provider.simpleBlockItem(block, provider.singleTextureItemModel(block, blockPath.replace("block/", "item/")));
    }
}
