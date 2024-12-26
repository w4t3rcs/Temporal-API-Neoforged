package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class BlockEntityTypeFactory implements ObjectFactory<BlockEntityType<?>> {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = IOHelper.createRegistry(Registries.BLOCK_ENTITY_TYPE);

    public Holder<BlockEntityType<?>> create(String name, BlockEntityType.BlockEntitySupplier<? extends BlockEntity> blockEntitySupplier, Block... blocks) {
        return this.create(name, () -> new BlockEntityType<>(blockEntitySupplier, Set.of(blocks) ));
    }

    @Override
    public Holder<BlockEntityType<?>> create(String name, Supplier<BlockEntityType<?>> entitySupplier) {
        return BLOCK_ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public void register() {
        BLOCK_ENTITY_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
