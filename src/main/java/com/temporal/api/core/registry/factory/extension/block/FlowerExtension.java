package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface FlowerExtension {
    default RegistryObject<FlowerBlock> createFlower(String name, BlockBehaviour.Properties properties, Holder<MobEffect> mobEffect, int duration) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<FlowerBlock>) blockFactory.createTyped(name, () -> new FlowerBlock(mobEffect, duration, properties.noOcclusion().noCollission()));
    }
    
    default RegistryObject<? extends FlowerBlock> createFlower(String name, Supplier<? extends FlowerBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends FlowerBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
