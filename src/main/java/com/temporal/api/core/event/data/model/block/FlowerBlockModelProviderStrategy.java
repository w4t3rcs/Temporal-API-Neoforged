package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FlowerBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        Block block = blockRegistry.get();
        String id = RegistryUtils.getIdFromBlock(block);
        Block pottedBlock = RegistryUtils.getBlockById("potted_" + id);
        blockModels.createPlantWithDefaultItem(block, pottedBlock, BlockModelGenerators.PlantType.NOT_TINTED);
    }
}
