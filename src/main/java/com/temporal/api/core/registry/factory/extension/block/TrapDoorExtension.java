package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface TrapDoorExtension {
    default DeferredBlock<Block> createTrapDoor(String name) {
        return createTrapDoor(name, BlockPropertiesFactory.trapDoor());
    }

    default DeferredBlock<Block> createTrapDoor(String name, BlockBehaviour.Properties properties) {
        return createTrapDoor(name, properties, BlockSetType.OAK);
    }

    default DeferredBlock<Block> createTrapDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        return createTrapDoor(name, properties, new Item.Properties(), setType);
    }

    default DeferredBlock<Block> createTrapDoor(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        return createTrapDoor(name, properties, itemProperties, BlockSetType.OAK);
    }

    default DeferredBlock<Block> createTrapDoor(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, BlockSetType setType) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new TrapDoorBlock(setType, props), itemProperties);
    }
}
