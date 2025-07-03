package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FlowerBlockModelProviderStrategy implements BlockModelProviderStrategy {
    private static final CrossBlockModelProviderStrategy CROSS_PROVIDER = new CrossBlockModelProviderStrategy();

    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider) {
        CROSS_PROVIDER.registerBlockModel(blockRegistry, provider);
        Block block = blockRegistry.get();
        String id = RegistryUtils.getIdFromBlock(block);
        Block pottedBlock = RegistryUtils.getBlockById("potted_" + id);
        String flowerPath = provider.getBlockPath(block);
        String potPath = provider.getBlockPath(pottedBlock);
        provider.simpleBlock(pottedBlock, provider.models()
                .withExistingParent(potPath, provider.mcLoc("block/flower_pot_cross"))
                .texture("plant", flowerPath)
                .renderType("minecraft:cutout"));
    }
}
