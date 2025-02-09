package com.temporal.api.core.event.data.biome.placement;

import com.temporal.api.core.engine.io.metadata.annotation.OreGeneration;
import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.ConfiguredFeaturesContainer;
import com.temporal.api.core.util.feature.OrePlacements;
import com.temporal.api.core.util.feature.PlacedFeatureUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class OrePlacedFeaturesGenerationProcess implements GenerationProcess<PlacedFeature, OreGeneration> {
    @Override
    public void bootstrap(BootstrapContext<PlacedFeature> context, DeferredBlock<?> block, OreGeneration description) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        String registryName = description.configuration().registry();
        var configuredFeatureResourceKey = ConfiguredFeaturesContainer.CONFIGURED_FEATURES.get(registryName);
        var configuredFeatureReference = lookup.getOrThrow(configuredFeatureResourceKey);
        String placedFeatureRegistryName = registryName + "_placed";
        ResourceKey<PlacedFeature> placedFeatureResourceKey = PlacedFeatureUtils.createKey(placedFeatureRegistryName);
        PlacedFeaturesContainer.PLACED_FEATURES.put(registryName, placedFeatureResourceKey);
        OreGeneration.Placement placement = description.placement();
        VerticalAnchor anchorFrom = VerticalAnchor.absolute(placement.from());
        VerticalAnchor anchorTo = VerticalAnchor.absolute(placement.to());
        HeightRangePlacement heightRangePlacement = switch (placement.shape()) {
            case UNIFORM -> HeightRangePlacement.uniform(anchorFrom, anchorTo);
            case TRIANGLE -> HeightRangePlacement.triangle(anchorFrom, anchorTo);
        };
        List<PlacementModifier> placementModifiers = switch (placement.rarity()) {
            case RARE -> OrePlacements.rareOrePlacement(placement.count(), heightRangePlacement);
            case COMMON -> OrePlacements.commonOrePlacement(placement.count(), heightRangePlacement);
        };
        PlacedFeatureUtils.register(context, placedFeatureResourceKey, configuredFeatureReference, placementModifiers);
    }
}
