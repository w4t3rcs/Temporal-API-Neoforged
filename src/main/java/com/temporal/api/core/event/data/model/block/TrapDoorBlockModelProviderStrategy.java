package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TrapDoorBlockModelProviderStrategy extends AbstractModelProviderStrategy<TrapDoorBlock> {
    public TrapDoorBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<TrapDoorBlock> blockRegistry) {
        TrapDoorBlock block = blockRegistry.get();
        this.getBlockModels().createOrientableTrapdoor(block);
    }
}
