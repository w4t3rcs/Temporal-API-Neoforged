package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.StrippableLogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface StrippableLogBlockExtension {
    default RegistryObject<StrippableLogBlock> createStrippableLog(String name, BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<StrippableLogBlock>) blockFactory.createTyped(name, () -> new StrippableLogBlock(strippedBlock.get(), properties));
    }

    default RegistryObject<? extends StrippableLogBlock> createStrippableLog(String name, Supplier<? extends StrippableLogBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends StrippableLogBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
