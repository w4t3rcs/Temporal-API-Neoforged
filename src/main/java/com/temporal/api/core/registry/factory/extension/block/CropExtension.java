package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface CropExtension {
    default DeferredBlock<CropBlock> createCrop(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<CropBlock>) blockFactory.createTyped(name, () -> new CropBlock(properties.noOcclusion().noCollission()));
    }

    default DeferredBlock<? extends CropBlock> createCrop(String name, Supplier<? extends CropBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return (DeferredBlock<? extends CropBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
