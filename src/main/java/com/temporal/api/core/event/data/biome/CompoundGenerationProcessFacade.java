package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.configuration.*;
import com.temporal.api.core.event.data.biome.modifier.*;
import com.temporal.api.core.event.data.biome.placement.*;
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
        executeBlockProcess(context, new TreeConfiguredFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
    }

    public static void executePlacedFeatures(BootstrapContext<PlacedFeature> context) {
        executeBlockProcess(context, new OrePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new FlowerPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassPlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
        executeBlockProcess(context, new VinePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.VINES);
        executeBlockProcess(context, new TreePlacedFeaturesGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
    }

    public static void executeBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        executeBlockProcess(context, new OreBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.ORES);
        executeBlockProcess(context, new FlowerBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.FLOWERS);
        executeBlockProcess(context, new GrassBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.GRASSES);
        executeBlockProcess(context, new VineBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.VINES);
        executeBlockProcess(context, new TreeBiomeModifiersGenerationProcess(), GenerationFeaturesDescriptionContainer.TREES);
    }

    private static <T, D> void executeBlockProcess(BootstrapContext<T> context, GenerationProcess<T, D> process, Map<DeferredBlock<?>, D> data) {
        data.forEach((key, value) -> process.bootstrap(context, key, value));
    }
}
