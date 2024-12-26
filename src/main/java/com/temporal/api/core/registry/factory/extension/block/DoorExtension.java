package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface DoorExtension {
    default DeferredBlock<DoorBlock> createDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<DoorBlock>) blockFactory.createTyped(name, () -> new DoorBlock(setType, properties));
    }

    default DeferredBlock<? extends DoorBlock> createDoor(String name, Supplier<? extends DoorBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends DoorBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
