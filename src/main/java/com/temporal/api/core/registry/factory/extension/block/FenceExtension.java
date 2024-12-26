package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface FenceExtension {
    default RegistryObject<FenceBlock> createFence(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<FenceBlock>) blockFactory.createTyped(name, () -> new FenceBlock(properties));
    }

    default RegistryObject<? extends FenceBlock> createFence(String name, Supplier<? extends FenceBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends FenceBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
