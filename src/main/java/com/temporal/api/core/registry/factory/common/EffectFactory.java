package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EffectFactory implements TypedFactory<MobEffect> {
    public static final DeferredRegister<MobEffect> EFFECTS = IOHelper.createRegistry(Registries.MOB_EFFECT);

    @Override
    public RegistryObject<MobEffect> create(String name, Supplier<MobEffect> mobEffectSupplier) {
        return EFFECTS.register(name, mobEffectSupplier);
    }

    @Override
    public RegistryObject<? extends MobEffect> createTyped(String name, Supplier<? extends MobEffect> mobEffectSupplier) {
        return EFFECTS.register(name, mobEffectSupplier);
    }

    @Override
    public void register() {
        EFFECTS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
