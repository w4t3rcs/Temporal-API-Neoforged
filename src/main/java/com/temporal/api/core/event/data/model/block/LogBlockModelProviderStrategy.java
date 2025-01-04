package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LogBlockModelProviderStrategy extends AbstractModelProviderStrategy<LogBlock> {
    public LogBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<LogBlock> blockRegistry) {
        LogBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation model = IOHelper.createResourceLocation(path);
        BlockStateGenerator generator = BlockModelGenerators.createMirroredColumnGenerator(block, model,
                TextureMapping.column(block), getBlockModels().modelOutput);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
