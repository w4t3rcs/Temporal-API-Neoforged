package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface FenceGateExtension {
    default DeferredBlock<FenceGateBlock> createFenceGate(String name, BlockBehaviour.Properties properties, WoodType woodType, SoundEvent openSound, SoundEvent closeSound) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<FenceGateBlock>) blockFactory.createTyped(name, () -> new FenceGateBlock(Optional.of(woodType), properties, Optional.of(openSound), Optional.of(closeSound)));
    }

    default DeferredBlock<? extends FenceGateBlock> createFenceGate(String name, Supplier<? extends FenceGateBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends FenceGateBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
