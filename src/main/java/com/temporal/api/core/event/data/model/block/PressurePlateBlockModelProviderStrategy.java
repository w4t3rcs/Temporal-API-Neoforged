package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PressurePlateBlockModelProviderStrategy extends AbstractModelProviderStrategy<PressurePlateBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<PressurePlateBlock> blockRegistry, BlockModelGenerators blockModels) {
        PressurePlateBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation unpoweredModel = ResourceUtils.createResourceLocation(path);
        ResourceLocation poweredModel = ResourceUtils.createResourceLocation(path + "_powered");
        BlockStateGenerator generator = BlockModelGenerators.createPressurePlate(block, unpoweredModel, poweredModel);
        blockModels.blockStateOutput.accept(generator);
    }
}
