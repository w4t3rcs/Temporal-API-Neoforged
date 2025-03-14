package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EffectFactory implements ObjectFactory<MobEffect> {
    public static final DeferredRegister<MobEffect> EFFECTS = RegistryUtils.createRegistry(Registries.MOB_EFFECT);

    @Override
    public Holder<MobEffect> create(String name, Supplier<MobEffect> mobEffectSupplier) {
        return EFFECTS.register(name, mobEffectSupplier);
    }

    @Override
    public void register() {
        EFFECTS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
