package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface TrapDoorExtension {
    default RegistryObject<TrapDoorBlock> createTrapDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<TrapDoorBlock>) blockFactory.createTyped(name, () -> new TrapDoorBlock(setType, properties));
    }

    default RegistryObject<? extends TrapDoorBlock> createTrapDoor(String name, Supplier<? extends TrapDoorBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends TrapDoorBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
