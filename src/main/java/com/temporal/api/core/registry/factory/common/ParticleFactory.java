package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ParticleFactory implements ObjectFactory<ParticleType<?>> {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = InjectionContext.getFromInstance("particle_types");

    public Holder<ParticleType<?>> create(String name, boolean overrideLimiter) {
        return create(name, () -> new SimpleParticleType(overrideLimiter));
    }

    @Override
    public Holder<ParticleType<?>> create(String name, Supplier<ParticleType<?>> particleTypeSupplier) {
        return PARTICLE_TYPES.register(name, particleTypeSupplier);
    }

    @Override
    public void register() {
        PARTICLE_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
