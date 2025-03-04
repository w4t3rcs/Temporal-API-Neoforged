package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ButtonBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ButtonBlockModelProviderStrategy extends AbstractModelProviderStrategy<ButtonBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<ButtonBlock> blockRegistry, BlockModelGenerators blockModels) {
        ButtonBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation unpoweredModel = ResourceUtils.createResourceLocation(path);
        ResourceLocation poweredModel = ResourceUtils.createResourceLocation(path + "_pressed");
        BlockStateGenerator generator = BlockModelGenerators.createButton(block, unpoweredModel, poweredModel);
        blockModels.blockStateOutput.accept(generator);
    }
}
