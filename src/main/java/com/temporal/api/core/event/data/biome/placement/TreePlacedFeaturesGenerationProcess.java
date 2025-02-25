package com.temporal.api.core.event.data.biome.placement;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.ConfiguredFeaturesContainer;
import com.temporal.api.core.event.data.biome.dto.Tree;
import com.temporal.api.core.util.biome.PlacedFeatureUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class TreePlacedFeaturesGenerationProcess implements GenerationProcess<PlacedFeature, Tree> {
    @Override
    public void bootstrap(BootstrapContext<PlacedFeature> context, DeferredBlock<?> block, Tree description) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeaturesContainer.CONFIGURED_FEATURES.get(registryName);
        var configuredFeatureReference = lookup.getOrThrow(configuredFeatureResourceKey);
        String placedFeatureRegistryName = registryName + "_placed";
        ResourceKey<PlacedFeature> placedFeatureResourceKey = PlacedFeatureUtils.createKey(placedFeatureRegistryName);
        PlacedFeaturesContainer.PLACED_FEATURES.put(registryName, placedFeatureResourceKey);
        Tree.Placement placement = description.placement();
        List<PlacementModifier> placementModifiers = VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(placement.baseValue(), placement.chance(), placement.addedAmount()),
                block.get()
        );
        PlacedFeatureUtils.register(context, placedFeatureResourceKey, configuredFeatureReference, placementModifiers);
    }
}
