package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface PressurePlateExtension {
    default DeferredBlock<Block> createPressurePlate(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        return createPressurePlate(name, properties, new Item.Properties(), setType);
    }

    default DeferredBlock<Block> createPressurePlate(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, BlockSetType setType) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new PressurePlateBlock(setType, props), itemProperties);
    }
}
