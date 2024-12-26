package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface LeavesExtension {
    default DeferredBlock<LeavesBlock> createLeaves(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<LeavesBlock>) blockFactory.createTyped(name, () -> new LeavesBlock(properties.noOcclusion()));
    }

    default DeferredBlock<? extends LeavesBlock> createLeaves(String name, Supplier<? extends LeavesBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends LeavesBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
