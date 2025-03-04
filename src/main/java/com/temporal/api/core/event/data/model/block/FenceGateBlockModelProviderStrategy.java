package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FenceGateBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceGateBlockModelProviderStrategy extends AbstractModelProviderStrategy<FenceGateBlock> {
    public FenceGateBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<FenceGateBlock> blockRegistry) {
        FenceGateBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation openModel = ResourceUtils.createResourceLocation(path + "_open");
        ResourceLocation closedModel = ResourceUtils.createResourceLocation(path + "_closed");
        ResourceLocation wallOpenModel = ResourceUtils.createResourceLocation(path + "_wall_open");
        ResourceLocation wallClosedModel = ResourceUtils.createResourceLocation(path + "_wall_closed");
        BlockStateGenerator generator = BlockModelGenerators.createFenceGate(block, openModel, closedModel, wallOpenModel, wallClosedModel, true);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
