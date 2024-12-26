package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface LogBlockExtension {
    default RegistryObject<LogBlock> createLog(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<LogBlock>) blockFactory.createTyped(name, () -> new LogBlock(properties));
    }

    default RegistryObject<? extends LogBlock> createLog(String name, Supplier<? extends LogBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends LogBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
