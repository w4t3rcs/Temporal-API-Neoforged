package com.temporal.api.core.event.data.biome;

import net.minecraft.data.worldgen.BootstrapContext;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface GenerationProcess<T, D> {
    void bootstrap(BootstrapContext<T> context, DeferredBlock<?> block, D description);
}
