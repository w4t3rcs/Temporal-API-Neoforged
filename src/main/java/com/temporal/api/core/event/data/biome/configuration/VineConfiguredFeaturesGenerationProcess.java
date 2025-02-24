package com.temporal.api.core.event.data.biome.configuration;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.dto.Vine;
import com.temporal.api.core.util.biome.ConfiguredFeatureUtils;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredBlock;

public class VineConfiguredFeaturesGenerationProcess implements GenerationProcess<ConfiguredFeature<?, ?>, Vine> {
    @Override
    public void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context, DeferredBlock<?> block, Vine description) {
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeatureUtils.createKey(registryName);
        ConfiguredFeaturesContainer.CONFIGURED_FEATURES.put(registryName, configuredFeatureResourceKey);
        ConfiguredFeatureUtils.register(context, configuredFeatureResourceKey, Feature.VINES, FeatureConfiguration.NONE);
    }
}
