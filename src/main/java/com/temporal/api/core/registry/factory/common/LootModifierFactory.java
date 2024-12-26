package com.temporal.api.core.registry.factory.common;

import com.mojang.serialization.MapCodec;
import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LootModifierFactory implements ObjectFactory<MapCodec<? extends IGlobalLootModifier>> {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = IOHelper.createRegistry(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    @Override
    public RegistryObject<MapCodec<? extends IGlobalLootModifier>> create(String name, Supplier<MapCodec<? extends IGlobalLootModifier>> codecSupplier) {
        return LOOT_MODIFIER_SERIALIZERS.register(name, codecSupplier);
    }

    @Override
    public void register() {
        LOOT_MODIFIER_SERIALIZERS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
