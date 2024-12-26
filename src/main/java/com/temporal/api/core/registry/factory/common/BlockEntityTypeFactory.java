package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Supplier;

public class BlockEntityTypeFactory implements TypedFactory<BlockEntityType<?>> {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = IOHelper.createRegistry(Registries.BLOCK_ENTITY_TYPE);

    public RegistryObject<BlockEntityType<?>> create(String name, BlockEntityType.BlockEntitySupplier<? extends BlockEntity> blockEntitySupplier, Block... blocks) {
        return this.create(name, () -> new BlockEntityType<>(blockEntitySupplier, Set.of(blocks) ));
    }

    @Override
    public RegistryObject<BlockEntityType<?>> create(String name, Supplier<BlockEntityType<?>> entitySupplier) {
        return BLOCK_ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public RegistryObject<? extends BlockEntityType<?>> createTyped(String name, Supplier<? extends BlockEntityType<?>> entitySupplier) {
        return BLOCK_ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public void register() {
        BLOCK_ENTITY_TYPES.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
