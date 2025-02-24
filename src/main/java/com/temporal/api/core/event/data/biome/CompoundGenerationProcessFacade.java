package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.configuration.FlowerConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.GrassConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.OreConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.configuration.VineConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.FlowerBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.GrassBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.OreBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.VineBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.FlowerPlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.GrassPlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.OrePlacedFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.VinePlacedFeaturesGenerationProcess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;

public final class CompoundGenerationProcessFacade {
    private CompoundGenerationProcessFacade() {
    }

    public static void executeConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        executeBlockProcess(context, new OreConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new FlowerConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
        executeBlockProcess(context, new VineConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.VINES);
    }

    public static void executePlacedFeatures(BootstrapContext<PlacedFeature> context) {
        executeBlockProcess(context, new OrePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new FlowerPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
        executeBlockProcess(context, new VinePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.VINES);
    }

    public static void executeBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        executeBlockProcess(context, new OreBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new FlowerBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
        executeBlockProcess(context, new VineBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.VINES);
    }

    private static <T, D> void executeBlockProcess(BootstrapContext<T> context, GenerationProcess<T, D> process, Map<DeferredBlock<?>, D> data) {
        data.forEach((key, value) -> process.bootstrap(context, key, value));
    }
}
