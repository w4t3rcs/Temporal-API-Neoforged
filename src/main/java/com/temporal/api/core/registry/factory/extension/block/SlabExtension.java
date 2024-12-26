package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SlabExtension {
    default RegistryObject<SlabBlock> createSlab(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<SlabBlock>) blockFactory.createTyped(name, () -> new SlabBlock(properties));
    }

    default RegistryObject<? extends SlabBlock> createSlab(String name, Supplier<? extends SlabBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends SlabBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
