package com.temporal.api.core.engine.event.registry;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EnginedRegisterFactory {
    public static <T> DeferredRegister<T> create(ResourceKey<Registry<T>> registry) {
        return DeferredRegister.create(registry, IOLayer.FORGE_MOD.getModId());
    }
}
