package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PressurePlateBlockModelProviderStrategy extends AbstractModelProviderStrategy<PressurePlateBlock> {
    public PressurePlateBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<PressurePlateBlock> blockRegistry) {
        PressurePlateBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation unpoweredModel = IOHelper.createResourceLocation(path);
        ResourceLocation poweredModel = IOHelper.createResourceLocation(path + "_powered");
        BlockStateGenerator generator = BlockModelGenerators.createPressurePlate(block, unpoweredModel, poweredModel);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
