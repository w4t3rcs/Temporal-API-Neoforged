package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface DoorExtension {
    default RegistryObject<DoorBlock> createDoor(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<DoorBlock>) blockFactory.createTyped(name, () -> new DoorBlock(setType, properties));
    }

    default RegistryObject<? extends DoorBlock> createDoor(String name, Supplier<? extends DoorBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends DoorBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
