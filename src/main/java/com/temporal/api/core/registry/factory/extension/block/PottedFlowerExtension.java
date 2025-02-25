package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public interface PottedFlowerExtension {
    default DeferredBlock<Block> createPot(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> flower) {
        return createPot(name, properties, new Item.Properties(), flower);
    }

    default DeferredBlock<Block> createPot(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, Supplier<? extends Block> flower) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion().noCollission(), props -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), flower, props), itemProperties);
    }
}
