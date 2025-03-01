package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.dto.Flower;
import com.temporal.api.core.event.data.biome.dto.Grass;
import com.temporal.api.core.event.data.biome.dto.Ore;
import com.temporal.api.core.event.data.biome.dto.Tree;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.HashMap;
import java.util.Map;

public final class GenerationFeaturesDescriptionContainer {
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Ore> ORES = new HashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Tree> TREES = new HashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Flower> FLOWERS = new HashMap<>();
    public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Grass> GRASSES = new HashMap<>();

    private GenerationFeaturesDescriptionContainer() {
    }
}
