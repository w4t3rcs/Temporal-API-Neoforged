package com.temporal.api.core.event.data.biome.placement;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.ConfiguredFeaturesContainer;
import com.temporal.api.core.event.data.biome.dto.Vine;
import com.temporal.api.core.util.biome.PlacedFeatureUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class VinePlacedFeaturesGenerationProcess implements GenerationProcess<PlacedFeature, Vine> {
    @Override
    public void bootstrap(BootstrapContext<PlacedFeature> context, DeferredBlock<?> block, Vine description) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeaturesContainer.CONFIGURED_FEATURES.get(registryName);
        var configuredFeatureReference = lookup.getOrThrow(configuredFeatureResourceKey);
        String placedFeatureRegistryName = registryName + "_placed";
        ResourceKey<PlacedFeature> placedFeatureResourceKey = PlacedFeatureUtils.createKey(placedFeatureRegistryName);
        PlacedFeaturesContainer.PLACED_FEATURES.put(registryName, placedFeatureResourceKey);
        Vine.Placement placement = description.placement();
        List<PlacementModifier> placementModifiers = List.of(
                CountPlacement.of(placement.count()),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(placement.from()), VerticalAnchor.absolute(placement.to())),
                BiomeFilter.biome()
        );
        PlacedFeatureUtils.register(context, placedFeatureResourceKey, configuredFeatureReference, placementModifiers);
    }
}
