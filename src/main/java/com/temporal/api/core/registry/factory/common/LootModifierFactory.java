package com.temporal.api.core.registry.factory.common;

import com.mojang.serialization.MapCodec;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LootModifierFactory implements ObjectFactory<MapCodec<? extends IGlobalLootModifier>> {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = InjectionContext.getFromInstance("global_loot_modifier_serializers");

    @Override
    public Holder<MapCodec<? extends IGlobalLootModifier>> create(String name, Supplier<MapCodec<? extends IGlobalLootModifier>> codecSupplier) {
        return LOOT_MODIFIER_SERIALIZERS.register(name, codecSupplier);
    }

    @Override
    public void register() {
        LOOT_MODIFIER_SERIALIZERS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
