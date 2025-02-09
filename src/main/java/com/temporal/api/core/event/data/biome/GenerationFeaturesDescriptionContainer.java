package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.engine.io.metadata.annotation.OreGeneration;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

public final class GenerationFeaturesDescriptionContainer {
    public static final Map<DeferredBlock<?>, OreGeneration> ORES = new HashMap<>();

    private GenerationFeaturesDescriptionContainer() {
    }
}
