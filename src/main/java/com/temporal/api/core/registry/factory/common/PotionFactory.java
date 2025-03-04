package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PotionFactory implements ObjectFactory<Potion> {
    public static final DeferredRegister<Potion> POTIONS = RegistryUtils.createRegistry(Registries.POTION);

    public Holder<Potion> create(String name, MobEffectInstance mobEffectInstance) {
        return create(name, () -> new Potion(name, mobEffectInstance));
    }

    public Holder<Potion> create(String name, Holder<MobEffect> effect, int duration) {
        return create(name, () -> new Potion(name, new MobEffectInstance(effect, duration)));
    }

    @Override
    public Holder<Potion> create(String name, Supplier<Potion> potionSupplier) {
        return POTIONS.register(name, potionSupplier);
    }

    @Override
    public void register() {
        POTIONS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
