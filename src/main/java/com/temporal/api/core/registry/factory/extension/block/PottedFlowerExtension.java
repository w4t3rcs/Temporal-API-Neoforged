package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public interface PottedFlowerExtension {
    default DeferredBlock<Block> createPottedFlower(String name, Supplier<? extends Block> flower) {
        return createPottedFlower(name, BlockPropertiesFactory.flowerPot(), flower);
    }

    @SuppressWarnings("deprecation")
    default DeferredBlock<Block> createPottedFlower(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> flower) {
        final BlockFactory blockFactory = InjectionPool.getFromInstance(BlockFactory.class);
        return blockFactory.createWithoutItem(name, properties, props -> new FlowerPotBlock(flower.get(), props));
    }
}
