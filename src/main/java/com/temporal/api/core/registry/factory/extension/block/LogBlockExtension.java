package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.common.block.StrippableLogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public interface LogBlockExtension {
    default DeferredBlock<Block> createStrippableLog(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        return createStrippableLog(name, properties, new Item.Properties(), strippedBlock);
    }

    default DeferredBlock<Block> createStrippableLog(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, Supplier<? extends Block> strippedBlock) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new StrippableLogBlock(strippedBlock.get(), props), itemProperties);
    }

    default DeferredBlock<Block> createLog(String name, BlockBehaviour.Properties properties) {
        return createLog(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createLog(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, LogBlock::new, itemProperties);
    }
}
