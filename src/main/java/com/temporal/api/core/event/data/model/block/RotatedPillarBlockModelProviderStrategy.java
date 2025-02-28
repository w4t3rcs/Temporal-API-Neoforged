package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class RotatedPillarBlockModelProviderStrategy extends AbstractModelProviderStrategy<RotatedPillarBlock> {
    public RotatedPillarBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<RotatedPillarBlock> blockRegistry) {
        RotatedPillarBlock block = blockRegistry.get();
        this.getBlockModels().createRotatedPillarWithHorizontalVariant(block, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
    }
}
