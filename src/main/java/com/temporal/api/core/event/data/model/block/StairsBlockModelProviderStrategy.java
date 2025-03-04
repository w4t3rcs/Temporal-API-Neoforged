package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StairsBlockModelProviderStrategy extends AbstractModelProviderStrategy<StairBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<StairBlock> blockRegistry, BlockModelGenerators blockModels) {
        StairBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation innerModel = ResourceUtils.createResourceLocation(path + "_inner");
        ResourceLocation straightModel = ResourceUtils.createResourceLocation(path);
        ResourceLocation outerModel = ResourceUtils.createResourceLocation(path + "_outer");
        BlockStateGenerator generator = BlockModelGenerators.createStairs(block, innerModel, straightModel, outerModel);
        blockModels.blockStateOutput.accept(generator);
    }
}
