package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface ButtonExtension {
    default DeferredBlock<Block> createButton(String name) {
        return createButton(name, BlockPropertiesFactory.button());
    }

    default DeferredBlock<Block> createButton(String name, BlockBehaviour.Properties properties) {
        return createButton(name, properties, BlockSetType.OAK, 30);
    }

    default DeferredBlock<Block> createButton(String name, BlockBehaviour.Properties properties, BlockSetType setType, int ticksToStayPressed) {
        return createButton(name, properties, new Item.Properties(), setType, ticksToStayPressed);
    }

    default DeferredBlock<Block> createButton(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        return createButton(name, properties, itemProperties, BlockSetType.OAK, 30);
    }

    default DeferredBlock<Block> createButton(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, BlockSetType setType, int ticksToStayPressed) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name,  properties, props -> new ButtonBlock(setType, ticksToStayPressed, props), itemProperties);
    }
}
