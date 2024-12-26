package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BiomeFactory implements ObjectFactory<Biome> {
    public static final DeferredRegister<Biome> BIOMES = IOHelper.createRegistry(Registries.BIOME);

    @Override
    public Holder<Biome> create(String name, Supplier<Biome> potionSupplier) {
        return BIOMES.register(name, potionSupplier);
    }

    @Override
    public void register() {
        BIOMES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
