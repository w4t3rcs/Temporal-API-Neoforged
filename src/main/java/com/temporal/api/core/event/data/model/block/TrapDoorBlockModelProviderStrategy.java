package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TrapDoorBlockModelProviderStrategy extends AbstractModelProviderStrategy<TrapDoorBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<TrapDoorBlock> blockRegistry, BlockModelGenerators blockModels) {
        TrapDoorBlock block = blockRegistry.get();
        blockModels.createOrientableTrapdoor(block);
    }
}
