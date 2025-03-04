package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LogBlockModelProviderStrategy extends AbstractModelProviderStrategy<LogBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<LogBlock> blockRegistry, BlockModelGenerators blockModels) {
        LogBlock block = blockRegistry.get();
        blockModels.woodProvider(block).log(block);
    }
}
