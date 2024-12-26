package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ParticleFactory implements TypedFactory<ParticleType<SimpleParticleType>> {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = IOHelper.createRegistry(Registries.PARTICLE_TYPE);

    public RegistryObject<ParticleType<SimpleParticleType>> create(String name, boolean overrideLimiter) {
        return create(name, () -> new SimpleParticleType(overrideLimiter));
    }

    @Override
    public RegistryObject<ParticleType<SimpleParticleType>> create(String name, Supplier<ParticleType<SimpleParticleType>> particleTypeSupplier) {
        return PARTICLE_TYPES.register(name, particleTypeSupplier);
    }

    @Override
    public RegistryObject<? extends ParticleType<SimpleParticleType>> createTyped(String name, Supplier<? extends ParticleType<SimpleParticleType>> tSupplier) {
        return PARTICLE_TYPES.register(name, tSupplier);
    }

    @Override
    public void register() {
        PARTICLE_TYPES.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
