package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ButtonBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ButtonBlockModelProviderStrategy extends AbstractModelProviderStrategy<ButtonBlock> {
    public ButtonBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<ButtonBlock> blockRegistry) {
        ButtonBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation unpoweredModel = IOHelper.createResourceLocation(path);
        ResourceLocation poweredModel = IOHelper.createResourceLocation(path + "_pressed");
        BlockStateGenerator generator = BlockModelGenerators.createButton(block, unpoweredModel, poweredModel);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
