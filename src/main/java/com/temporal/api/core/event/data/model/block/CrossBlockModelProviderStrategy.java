package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CrossBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        Block block = blockRegistry.get();
        BlockModelGenerators.PlantType plantType = BlockModelGenerators.PlantType.NOT_TINTED;
        ResourceLocation location = plantType.getCross()
                .extend()
                .renderType("minecraft:cutout")
                .build()
                .create(block, plantType.getTextureMapping(block), blockModels.modelOutput);
        ResourceLocation itemModel = blockModels.createFlatItemModelWithBlockTexture(block.asItem(), block);
        blockModels.registerSimpleItemModel(block, itemModel);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, location));
    }
}
