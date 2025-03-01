package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.configuration.FlowerConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.GrassConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.OreConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.TreeConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.FlowerBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.GrassBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.OreBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.TreeBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.FlowerPlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.GrassPlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.OrePlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.TreePlacedFeaturesGenerationProcess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;

import java.util.Map;

public final class CompoundGenerationProcessFacade {
    private CompoundGenerationProcessFacade() {
    }

    public static void executeConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        executeBlockProcess(context, new OreConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new TreeConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
        executeBlockProcess(context, new FlowerConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
    }

    public static void executePlacedFeatures(BootstrapContext<PlacedFeature> context) {
        executeBlockProcess(context, new OrePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new TreePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
        executeBlockProcess(context, new FlowerPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
    }

    public static void executeBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        executeBlockProcess(context, new OreBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new TreeBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
        executeBlockProcess(context, new FlowerBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
    }

    private static <T, D> void executeBlockProcess(BootstrapContext<T> context, GenerationProcess<T, D> process, Map<ResourceKey<ConfiguredFeature<?, ?>>, D> data) {
        data.forEach((key, value) -> process.bootstrap(context, key, value));
    }
}
