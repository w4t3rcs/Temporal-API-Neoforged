package com.temporal.api.core.registry.factory.common;

import com.mojang.datafixers.types.Type;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class BlockEntityTypeFactory implements ObjectFactory<BlockEntityType<?>> {
    private final DeferredRegister<BlockEntityType<?>> blockEntityTypes;

    public BlockEntityTypeFactory() {
        this(InjectionContext.getFromInstance("block_entity_types"));
    }

    public BlockEntityTypeFactory(DeferredRegister<BlockEntityType<?>> blockEntityTypes) {
        this.blockEntityTypes = blockEntityTypes;
    }

    public Holder<BlockEntityType<?>> create(String name, BlockEntityType.BlockEntitySupplier<? extends BlockEntity> blockEntitySupplier, Type<?> dataType, Block... blocks) {
        return this.create(name, () -> new BlockEntityType<>(blockEntitySupplier, Set.of(blocks), dataType));
    }

    @Override
    public Holder<BlockEntityType<?>> create(String name, Supplier<BlockEntityType<?>> entitySupplier) {
        return blockEntityTypes.register(name, entitySupplier);
    }

    @Override
    public void register() {
        blockEntityTypes.register(InjectionContext.getFromInstance(IEventBus.class));
    }

    @Override
    public DeferredRegister<BlockEntityType<?>> getRegistry() {
        return blockEntityTypes;
    }
}
