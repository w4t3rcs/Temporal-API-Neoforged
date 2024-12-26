package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.StrippableLogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface StrippableLogBlockExtension {
    default DeferredBlock<StrippableLogBlock> createStrippableLog(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<StrippableLogBlock>) blockFactory.createTyped(name, () -> new StrippableLogBlock(strippedBlock.get(), properties));
    }

    default DeferredBlock<? extends StrippableLogBlock> createStrippableLog(String name, Supplier<? extends StrippableLogBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends StrippableLogBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
