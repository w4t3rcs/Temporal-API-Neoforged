package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface PottedFlowerExtension {
    default DeferredBlock<FlowerPotBlock> createPot(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> flower) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<FlowerPotBlock>) blockFactory.createTyped(name, () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), flower, properties.noOcclusion().noCollission()));
    }

    default DeferredBlock<? extends FlowerPotBlock> createPot(String name, Supplier<? extends FlowerPotBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends FlowerPotBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
