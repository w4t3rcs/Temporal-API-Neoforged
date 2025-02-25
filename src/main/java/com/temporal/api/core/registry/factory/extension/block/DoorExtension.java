package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface DoorExtension {
    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        return createDoor(name, properties, new Item.Properties(), setType);
    }

    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, BlockSetType setType) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new DoorBlock(setType, props), itemProperties);
    }
}
