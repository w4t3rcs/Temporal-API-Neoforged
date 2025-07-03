package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PotionFactory implements ObjectFactory<Potion> {
    private final DeferredRegister<Potion> potions;

    public PotionFactory() {
        this(InjectionContext.getFromInstance("potions"));
    }

    public PotionFactory(DeferredRegister<Potion> potions) {
        this.potions = potions;
    }

    public Holder<Potion> create(String name, MobEffectInstance mobEffectInstance) {
        return create(name, () -> new Potion(name, mobEffectInstance));
    }

    public Holder<Potion> create(String name, Holder<MobEffect> effect, int duration) {
        return create(name, () -> new Potion(name, new MobEffectInstance(effect, duration)));
    }

    @Override
    public Holder<Potion> create(String name, Supplier<Potion> potionSupplier) {
        return potions.register(name, potionSupplier);
    }

    @Override
    public DeferredRegister<Potion> getRegistry() {
        return potions;
    }
}
