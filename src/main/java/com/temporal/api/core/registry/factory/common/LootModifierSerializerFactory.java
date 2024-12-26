package com.temporal.api.core.registry.factory.common;

import com.mojang.serialization.MapCodec;
import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class LootModifierSerializerFactory implements ObjectFactory<MapCodec<? extends IGlobalLootModifier>> {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = IOHelper.createRegistry(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    @Override
    public Holder<MapCodec<? extends IGlobalLootModifier>> create(String name, Supplier<MapCodec<? extends IGlobalLootModifier>> codecSupplier) {
        return GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(name, codecSupplier);
    }

    @Override
    public void register() {
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
