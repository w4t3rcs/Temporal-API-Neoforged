package com.temporal.api.core.event.data.biome.configuration;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.dto.Grass;
import com.temporal.api.core.util.biome.ConfiguredFeatureUtils;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.neoforged.neoforge.registries.DeferredBlock;

public class GrassConfiguredFeaturesGenerationProcess implements GenerationProcess<ConfiguredFeature<?, ?>, Grass> {
    @Override
    public void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context, DeferredBlock<?> block, Grass description) {
        Grass.Configuration configuration = description.configuration();
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeatureUtils.createKey(registryName);
        ConfiguredFeaturesContainer.CONFIGURED_FEATURES.put(registryName, configuredFeatureResourceKey);
        ConfiguredFeatureUtils.register(context, configuredFeatureResourceKey, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(configuration.tries(), PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block.get())))));
    }
}
