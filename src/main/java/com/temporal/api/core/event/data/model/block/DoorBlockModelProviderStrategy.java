package com.temporal.api.core.event.data.model.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.DoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels) {
        DoorBlock block = (DoorBlock) blockRegistry.get();
        blockModels.createDoor(block);
    }
}
