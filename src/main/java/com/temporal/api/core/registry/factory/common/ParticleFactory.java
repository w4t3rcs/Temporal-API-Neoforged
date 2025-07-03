package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ParticleFactory implements ObjectFactory<ParticleType<?>> {
    private final DeferredRegister<ParticleType<?>> particleTypes;

    public ParticleFactory() {
        this(InjectionContext.getFromInstance("particle_types"));
    }

    public ParticleFactory(DeferredRegister<ParticleType<?>> particleTypes) {
        this.particleTypes = particleTypes;
    }

    public Holder<ParticleType<?>> create(String name, boolean overrideLimiter) {
        return create(name, () -> new SimpleParticleType(overrideLimiter));
    }

    @Override
    public Holder<ParticleType<?>> create(String name, Supplier<ParticleType<?>> particleTypeSupplier) {
        return particleTypes.register(name, particleTypeSupplier);
    }

    @Override
    public DeferredRegister<ParticleType<?>> getRegistry() {
        return particleTypes;
    }
}
