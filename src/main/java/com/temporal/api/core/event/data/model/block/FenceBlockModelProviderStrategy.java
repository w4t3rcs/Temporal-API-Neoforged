package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FenceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        FenceBlock block = (FenceBlock) blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation postModel = ResourceUtils.createResourceLocation(path + "_post");
        ResourceLocation sideModel = ResourceUtils.createResourceLocation(path + "_side");
        BlockStateGenerator generator = BlockModelGenerators.createFence(block, postModel, sideModel);
        blockModels.blockStateOutput.accept(generator);
    }
}
