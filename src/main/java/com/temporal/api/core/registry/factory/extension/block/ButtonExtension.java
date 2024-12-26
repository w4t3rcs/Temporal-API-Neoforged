package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ButtonExtension {
    default RegistryObject<ButtonBlock> createButton(String name, BlockBehaviour.Properties properties, BlockSetType setType, int ticksToStayPressed) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<ButtonBlock>) blockFactory.createTyped(name, () -> new ButtonBlock(setType, ticksToStayPressed, properties));
    }

    default RegistryObject<? extends ButtonBlock> createButton(String name, Supplier<? extends ButtonBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends ButtonBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
