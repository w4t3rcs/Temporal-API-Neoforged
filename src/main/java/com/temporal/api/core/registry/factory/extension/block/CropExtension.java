package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface CropExtension {
    default RegistryObject<CropBlock> createCrop(String name, BlockBehaviour.Properties properties) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<CropBlock>) blockFactory.createTyped(name, () -> new CropBlock(properties.noOcclusion().noCollission()));
    }

    default RegistryObject<? extends CropBlock> createCrop(String name, Supplier<? extends CropBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends CropBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
