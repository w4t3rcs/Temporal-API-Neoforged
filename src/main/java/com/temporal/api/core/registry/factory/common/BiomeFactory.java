package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BiomeFactory implements TypedFactory<Biome> {
    public static final DeferredRegister<Biome> BIOMES = IOHelper.createRegistry(Registries.BIOME);

    @Override
    public RegistryObject<Biome> create(String name, Supplier<Biome> potionSupplier) {
        return BIOMES.register(name, potionSupplier);
    }

    @Override
    public RegistryObject<? extends Biome> createTyped(String name, Supplier<? extends Biome> tSupplier) {
        return BIOMES.register(name, tSupplier);
    }

    @Override
    public void register() {
        BIOMES.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
