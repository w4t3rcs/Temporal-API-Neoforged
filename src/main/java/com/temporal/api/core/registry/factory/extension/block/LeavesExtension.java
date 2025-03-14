package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface LeavesExtension {
    default DeferredBlock<Block> createLeaves(String name) {
        return createLeaves(name, BlockPropertiesFactory.leaves());
    }

    default DeferredBlock<Block> createLeaves(String name, BlockBehaviour.Properties properties) {
        return createLeaves(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createLeaves(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion(), LeavesBlock::new, itemProperties);
    }
}
