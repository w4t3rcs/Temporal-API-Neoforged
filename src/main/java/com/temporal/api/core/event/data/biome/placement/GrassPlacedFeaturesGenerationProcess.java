package com.temporal.api.core.event.data.biome.placement;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.ConfiguredFeaturesContainer;
import com.temporal.api.core.event.data.biome.dto.Grass;
import com.temporal.api.core.util.biome.PlacedFeatureUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class GrassPlacedFeaturesGenerationProcess implements GenerationProcess<PlacedFeature, Grass> {
    @Override
    public void bootstrap(BootstrapContext<PlacedFeature> context, DeferredBlock<?> block, Grass description) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeaturesContainer.CONFIGURED_FEATURES.get(registryName);
        var configuredFeatureReference = lookup.getOrThrow(configuredFeatureResourceKey);
        String placedFeatureRegistryName = registryName + "_placed";
        ResourceKey<PlacedFeature> placedFeatureResourceKey = PlacedFeatureUtils.createKey(placedFeatureRegistryName);
        PlacedFeaturesContainer.PLACED_FEATURES.put(registryName, placedFeatureResourceKey);
        Grass.Placement placement = description.placement();
        List<PlacementModifier> placementModifiers = VegetationPlacements.worldSurfaceSquaredWithCount(placement.count());
        PlacedFeatureUtils.register(context, placedFeatureResourceKey, configuredFeatureReference, placementModifiers);
    }
}
