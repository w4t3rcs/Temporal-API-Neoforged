package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EffectFactory implements ObjectFactory<MobEffect> {
    private final DeferredRegister<MobEffect> mobEffects;

    public EffectFactory() {
        this(InjectionPool.getFromInstance("$MobEffects"));
    }

    public EffectFactory(DeferredRegister<MobEffect> mobEffects) {
        this.mobEffects = mobEffects;
    }

    @Override
    public Holder<MobEffect> create(String name, Supplier<MobEffect> mobEffectSupplier) {
        return mobEffects.register(name, mobEffectSupplier);
    }

    @Override
    public DeferredRegister<MobEffect> getRegistry() {
        return mobEffects;
    }
}
