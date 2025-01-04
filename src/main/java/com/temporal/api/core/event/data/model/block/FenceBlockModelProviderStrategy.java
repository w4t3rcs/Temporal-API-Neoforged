package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FenceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceBlockModelProviderStrategy extends AbstractModelProviderStrategy<FenceBlock> {
    public FenceBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<FenceBlock> blockRegistry) {
        FenceBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation postModel = IOHelper.createResourceLocation(path + "_post");
        ResourceLocation sideModel = IOHelper.createResourceLocation(path + "_side");
        BlockStateGenerator generator = BlockModelGenerators.createFence(block, postModel, sideModel);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
