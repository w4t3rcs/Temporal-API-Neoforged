package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.TypedFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface FenceGateExtension {
    default RegistryObject<FenceGateBlock> createFenceGate(String name, BlockBehaviour.Properties properties, WoodType woodType, SoundEvent openSound, SoundEvent closeSound) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<FenceGateBlock>) blockFactory.createTyped(name, () -> new FenceGateBlock(woodType, properties, openSound, closeSound));
    }

    default RegistryObject<? extends FenceGateBlock> createFenceGate(String name, Supplier<? extends FenceGateBlock> tTypedSupplier) {
        final TypedFactory<Block> blockFactory = InjectionContext.getInstance().getObject(BlockFactory.class);
        return (RegistryObject<? extends FenceGateBlock>) blockFactory.createTyped(name, tTypedSupplier);
    }
}
