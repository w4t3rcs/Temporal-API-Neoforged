package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface DoorExtension {
    default DeferredBlock<Block> createDoor(String name) {
        return createDoor(name, BlockPropertiesFactory.door());
    }

    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties) {
        return createDoor(name, properties, BlockSetType.OAK);
    }

    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        return createDoor(name, properties, new Item.Properties(), setType);
    }

    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        return createDoor(name, properties, itemProperties, BlockSetType.OAK);
    }
    
    default DeferredBlock<Block> createDoor(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, BlockSetType setType) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new DoorBlock(setType, props), itemProperties);
    }
}
