package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BiomeFactory implements ObjectFactory<Biome> {
    public static final DeferredRegister<Biome> BIOMES = InjectionContext.getFromInstance("biomes");

    @Override
    public Holder<Biome> create(String name, Supplier<Biome> biomeSupplier) {
        return BIOMES.register(name, biomeSupplier);
    }

    @Override
    public void register() {
        BIOMES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
