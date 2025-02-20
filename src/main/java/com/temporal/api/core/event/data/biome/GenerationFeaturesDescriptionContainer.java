package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.dto.Ore;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

public final class GenerationFeaturesDescriptionContainer {
    public static final Map<DeferredBlock<?>, Ore> ORES = new HashMap<>();

    private GenerationFeaturesDescriptionContainer() {
    }
}
