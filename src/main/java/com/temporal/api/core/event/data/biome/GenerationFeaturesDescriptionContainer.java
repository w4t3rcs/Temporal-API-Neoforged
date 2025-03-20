package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.collection.TemporalHashMap;
import com.temporal.api.core.event.data.biome.dto.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Map;

public final class GenerationFeaturesDescriptionContainer {
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Ore> ORES = new TemporalHashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Tree> TREES = new TemporalHashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Flower> FLOWERS = new TemporalHashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Grass> GRASSES = new TemporalHashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, WorldFeatureDefinitionContainer> DEFINITIONS = new TemporalHashMap<>();

    private GenerationFeaturesDescriptionContainer() {
    }
}
