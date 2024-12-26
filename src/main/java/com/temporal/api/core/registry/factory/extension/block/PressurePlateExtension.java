package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface PressurePlateExtension {
    default DeferredBlock<PressurePlateBlock> createPressurePlate(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<PressurePlateBlock>) blockFactory.createTyped(name, () -> new PressurePlateBlock(setType, properties));
    }

    default DeferredBlock<? extends PressurePlateBlock> createPressurePlate(String name, Supplier<? extends PressurePlateBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends PressurePlateBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
