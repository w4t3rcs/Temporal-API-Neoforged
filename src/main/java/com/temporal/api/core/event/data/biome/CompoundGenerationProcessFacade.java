package com.temporal.api.core.event.data.biome;

import com.temporal.api.core.event.data.biome.configuration.OreConfiguredFeaturesGenerationProcess;
import com.temporal.api.core.event.data.biome.modifier.OreBiomeModifiersGenerationProcess;
import com.temporal.api.core.event.data.biome.placement.OrePlacedFeaturesGenerationProcess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;

public final class CompoundGenerationProcessFacade {
    private CompoundGenerationProcessFacade() {
    }

    public static void executeConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var oreGenerationProcess = new OreConfiguredFeaturesGenerationProcess();
        GenerationFeaturesDescriptionContainer.ORES.forEach((block, description) -> {
            oreGenerationProcess.bootstrap(context, block, description);
        });
    }

    public static void executePlacedFeatures(BootstrapContext<PlacedFeature> context) {
        var oreGenerationProcess = new OrePlacedFeaturesGenerationProcess();
        GenerationFeaturesDescriptionContainer.ORES.forEach((block, description) -> {
            oreGenerationProcess.bootstrap(context, block, description);
        });
    }

    public static void executeBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        var oreGenerationProcess = new OreBiomeModifiersGenerationProcess();
        GenerationFeaturesDescriptionContainer.ORES.forEach((block, description) -> {
            oreGenerationProcess.bootstrap(context, block, description);
        });
    }
}
