package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FlowerBlockModelProviderStrategy implements BlockModelProviderStrategy {
    private static final CrossBlockModelProviderStrategy CROSS_PROVIDER = new CrossBlockModelProviderStrategy();

    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        CROSS_PROVIDER.registerBlockModel(blockRegistry, provider);
        Block block = blockRegistry.get();
        String id = RegistryUtils.getIdFromRegistry(BuiltInRegistries.BLOCK, block);
        String pottedId = RegistryUtils.mapId(id, (path) -> "potted_" + path);
        Block pottedBlock = RegistryUtils.getBlockById(pottedId);
        String flowerPath = provider.getBlockPath(block);
        String pottedPath = provider.getBlockPath(pottedBlock);
        provider.simpleBlock(pottedBlock, provider.models()
                .withExistingParent(pottedPath, provider.mcLoc("block/flower_pot_cross"))
                .texture("plant", flowerPath)
                .renderType("minecraft:cutout"));
    }
}
