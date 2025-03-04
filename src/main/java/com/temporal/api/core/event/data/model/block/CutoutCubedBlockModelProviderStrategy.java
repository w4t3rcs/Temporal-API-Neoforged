package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CutoutCubedBlockModelProviderStrategy extends AbstractModelProviderStrategy<Block> {
    @Override
    public void registerBlockModel(DeferredBlock<Block> blockRegistry, BlockModelGenerators blockModels) {
        final Block block = blockRegistry.get();
        ResourceLocation location = ModelTemplates.CUBE_ALL
                .extend()
                .renderType("minecraft:cutout")
                .build()
                .create(block, TextureMapping.cube(block), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, location));
    }
}
