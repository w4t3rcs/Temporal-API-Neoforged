package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public interface PottedFlowerExtension {
    @SuppressWarnings("deprecation")
    default DeferredBlock<Block> createPottedFlower(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> flower) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.createWithoutItem(name, properties, props -> new FlowerPotBlock(flower.get(), props));
    }
}
