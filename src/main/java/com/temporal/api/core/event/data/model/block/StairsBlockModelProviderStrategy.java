package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StairsBlockModelProviderStrategy extends AbstractModelProviderStrategy<StairBlock> {
    public StairsBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<StairBlock> blockRegistry) {
        StairBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation innerModel = IOHelper.createResourceLocation(path + "_inner");
        ResourceLocation straightModel = IOHelper.createResourceLocation(path);
        ResourceLocation outerModel = IOHelper.createResourceLocation(path + "_outer");
        BlockStateGenerator generator = BlockModelGenerators.createStairs(block, innerModel, straightModel, outerModel);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
