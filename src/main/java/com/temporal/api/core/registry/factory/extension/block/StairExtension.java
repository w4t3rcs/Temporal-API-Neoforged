package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface StairExtension {
    default RegistryObject<StairBlock> createStair(String name, Block block, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<StairBlock>) blockFactory.createTyped(name, () -> new StairBlock(block.defaultBlockState(), properties));
    }

    default RegistryObject<? extends StairBlock> createStair(String name, Supplier<? extends StairBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends StairBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
