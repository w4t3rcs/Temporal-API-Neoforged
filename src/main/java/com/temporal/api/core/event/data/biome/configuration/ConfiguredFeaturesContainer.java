package com.temporal.api.core.event.data.biome.configuration;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.HashMap;
import java.util.Map;

public final class ConfiguredFeaturesContainer {
    public static final Map<String, ResourceKey<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURES = new HashMap<>();

    private ConfiguredFeaturesContainer() {
    }
}
