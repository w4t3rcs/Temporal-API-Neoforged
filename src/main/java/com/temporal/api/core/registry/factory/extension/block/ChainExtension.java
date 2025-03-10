package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface ChainExtension {
    default DeferredBlock<Block> createChain(String name) {
        return createChain(name, BlockPropertiesFactory.chain());
    }

    default DeferredBlock<Block> createChain(String name, BlockBehaviour.Properties properties) {
        return createChain(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createChain(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.sound(SoundType.CHAIN).noOcclusion(), ChainBlock::new, itemProperties);
    }
}
