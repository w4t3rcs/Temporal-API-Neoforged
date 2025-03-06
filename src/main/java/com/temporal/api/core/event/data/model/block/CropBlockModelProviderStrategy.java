package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CropBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        CropBlock block = (CropBlock) blockRegistry.get();
        blockModels.createCropBlock(block, IntegerProperty.create("age", 0, block.getMaxAge()));
    }
}
