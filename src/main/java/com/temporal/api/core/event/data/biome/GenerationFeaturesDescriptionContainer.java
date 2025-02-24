package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.dto.Flower;
import com.temporal.api.core.event.data.biome.dto.Grass;
import com.temporal.api.core.event.data.biome.dto.Ore;
import com.temporal.api.core.event.data.biome.dto.Vine;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

public final class GenerationFeaturesDescriptionContainer {
    public static final Map<DeferredBlock<?>, Ore> ORES = new HashMap<>();
    public static final Map<DeferredBlock<?>, Flower> FLOWERS = new HashMap<>();
    public static final Map<DeferredBlock<?>, Grass> GRASSES = new HashMap<>();
    public static final Map<DeferredBlock<?>, Vine> VINES = new HashMap<>();

    private GenerationFeaturesDescriptionContainer() {
    }
}
