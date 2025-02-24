package com.temporal.api.core.event.data.biome.placement;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.ConfiguredFeaturesContainer;
import com.temporal.api.core.event.data.biome.dto.Flower;
import com.temporal.api.core.util.biome.PlacedFeatureUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class FlowerPlacedFeaturesGenerationProcess implements GenerationProcess<PlacedFeature, Flower> {
    @Override
    public void bootstrap(BootstrapContext<PlacedFeature> context, DeferredBlock<?> block, Flower description) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeaturesContainer.CONFIGURED_FEATURES.get(registryName);
        var configuredFeatureReference = lookup.getOrThrow(configuredFeatureResourceKey);
        String placedFeatureRegistryName = registryName + "_placed";
        ResourceKey<PlacedFeature> placedFeatureResourceKey = PlacedFeatureUtils.createKey(placedFeatureRegistryName);
        PlacedFeaturesContainer.PLACED_FEATURES.put(registryName, placedFeatureResourceKey);
        Flower.Placement placement = description.placement();
        List<PlacementModifier> placementModifiers = List.of(
                NoiseThresholdCountPlacement.of(placement.noiseLevel(), placement.belowNoise(), placement.aboveNoise()),
                RarityFilter.onAverageOnceEvery(placement.chance()),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacedFeatureUtils.register(context, placedFeatureResourceKey, configuredFeatureReference, placementModifiers);
    }
}
