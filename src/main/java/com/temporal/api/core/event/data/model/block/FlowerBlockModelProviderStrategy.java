package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FlowerBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    private static final CrossBlockModelProviderStrategy CROSS_PROVIDER = new CrossBlockModelProviderStrategy();

    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        Block block = blockRegistry.get();
        String id = RegistryUtils.getIdFromBlock(block);
        Block pottedBlock = RegistryUtils.getBlockById("potted_" + id);
        BlockModelGenerators.PlantType plantType = BlockModelGenerators.PlantType.NOT_TINTED;
        CROSS_PROVIDER.registerBlockModel(blockRegistry, blockModels);
        TextureMapping texturemapping = plantType.getPlantTextureMapping(block);
        ResourceLocation resourcelocation = plantType.getCrossPot().create(pottedBlock, texturemapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pottedBlock, resourcelocation));
    }
}
