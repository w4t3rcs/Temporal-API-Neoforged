package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface TrapDoorExtension {
    default DeferredBlock<Block> createTrapDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new TrapDoorBlock(setType, props));
    }
}
