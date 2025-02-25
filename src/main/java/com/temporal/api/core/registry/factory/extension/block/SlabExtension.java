package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SlabExtension {
    default DeferredBlock<Block> createSlab(String name, BlockBehaviour.Properties properties) {
        return createSlab(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createSlab(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, SlabBlock::new, itemProperties);
    }
}
