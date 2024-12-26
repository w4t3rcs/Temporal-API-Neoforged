package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ChainExtension {
    default DeferredBlock<ChainBlock> createBush(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<ChainBlock>) blockFactory.createTyped(name, () -> new ChainBlock(properties.sound(SoundType.CHAIN).noOcclusion()));
    }

    default DeferredBlock<? extends ChainBlock> createBush(String name, Supplier<? extends ChainBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends ChainBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
