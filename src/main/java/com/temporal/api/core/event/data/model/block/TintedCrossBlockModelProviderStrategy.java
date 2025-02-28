package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TintedCrossBlockModelProviderStrategy extends AbstractModelProviderStrategy<Block> {
    public TintedCrossBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<Block> blockRegistry) {
        final Block block = blockRegistry.get();
        this.getBlockModels().createCrossBlock(block, BlockModelGenerators.PlantType.TINTED);
    }
}
