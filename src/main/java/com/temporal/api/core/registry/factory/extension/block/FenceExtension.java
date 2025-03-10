package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface FenceExtension {
    default DeferredBlock<Block> createFence(String name) {
        return createFence(name, BlockPropertiesFactory.fence());
    }

    default DeferredBlock<Block> createFence(String name, BlockBehaviour.Properties properties) {
        return createFence(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createFence(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, FenceBlock::new, itemProperties);
    }
}
