package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.StrippableLogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public interface StrippableLogBlockExtension {
    default DeferredBlock<Block> createStrippableLog(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, props -> new StrippableLogBlock(strippedBlock.get(), props));
    }
}
