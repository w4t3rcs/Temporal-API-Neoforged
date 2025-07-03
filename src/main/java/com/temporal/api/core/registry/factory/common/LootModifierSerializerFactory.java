package com.temporal.api.core.registry.factory.common;

import com.mojang.serialization.MapCodec;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LootModifierSerializerFactory implements ObjectFactory<MapCodec<? extends IGlobalLootModifier>> {
    private final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> lootModifierSerializers;

    public LootModifierSerializerFactory() {
        this(InjectionContext.getFromInstance("global_loot_modifier_serializers"));
    }

    public LootModifierSerializerFactory(DeferredRegister<MapCodec<? extends IGlobalLootModifier>> lootModifierSerializers) {
        this.lootModifierSerializers = lootModifierSerializers;
    }

    @Override
    public Holder<MapCodec<? extends IGlobalLootModifier>> create(String name, Supplier<MapCodec<? extends IGlobalLootModifier>> codecSupplier) {
        return lootModifierSerializers.register(name, codecSupplier);
    }

    @Override
    public DeferredRegister<MapCodec<? extends IGlobalLootModifier>> getRegistry() {
        return lootModifierSerializers;
    }
}
