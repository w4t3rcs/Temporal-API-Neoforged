package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface LogBlockExtension {
    default DeferredBlock<LogBlock> createLog(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<LogBlock>) blockFactory.createTyped(name, () -> new LogBlock(properties));
    }

    default DeferredBlock<? extends LogBlock> createLog(String name, Supplier<? extends LogBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends LogBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
