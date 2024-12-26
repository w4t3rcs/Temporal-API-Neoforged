package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface PressurePlateExtension {
    default RegistryObject<PressurePlateBlock> createPressurePlate(String name, BlockBehaviour.Properties properties, BlockSetType setType) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<PressurePlateBlock>) blockFactory.createTyped(name, () -> new PressurePlateBlock(setType, properties));
    }

    default RegistryObject<? extends PressurePlateBlock> createPressurePlate(String name, Supplier<? extends PressurePlateBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends PressurePlateBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
