package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LogBlockModelProviderStrategy extends AbstractModelProviderStrategy<LogBlock> {
    public LogBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<LogBlock> blockRegistry) {
        LogBlock block = blockRegistry.get();
        this.getBlockModels().woodProvider(block).log(block);
    }
}
