package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BiomeFactory implements ObjectFactory<Biome> {
    private final DeferredRegister<Biome> biomes;

    public BiomeFactory() {
        this(InjectionPool.getFromInstance("$Biomes"));
    }

    public BiomeFactory(DeferredRegister<Biome> biomes) {
        this.biomes = biomes;
    }

    @Override
    public Holder<Biome> create(String name, Supplier<Biome> biomeSupplier) {
        return biomes.register(name, biomeSupplier);
    }

    @Override
    public DeferredRegister<Biome> getRegistry() {
        return biomes;
    }
}
