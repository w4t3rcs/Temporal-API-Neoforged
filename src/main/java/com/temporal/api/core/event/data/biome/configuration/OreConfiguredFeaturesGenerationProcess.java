package com.temporal.api.core.event.data.biome.configuration;

import com.temporal.api.core.engine.io.metadata.annotation.OreGeneration;
import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.util.biome.ConfiguredFeatureUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Arrays;
import java.util.Objects;

public class OreConfiguredFeaturesGenerationProcess implements GenerationProcess<ConfiguredFeature<?, ?>, OreGeneration> {
    @Override
    public void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context, DeferredBlock<?> block, OreGeneration description) {
        OreGeneration.Configuration configuration = description.configuration();
        var configuredFeatureResourceKey = ConfiguredFeatureUtils.createKey(configuration.registry());
        ConfiguredFeaturesContainer.CONFIGURED_FEATURES.put(configuration.registry(), configuredFeatureResourceKey);
        String[] replaceableBlocks = configuration.replaceableBlocks();
        var rules = BuiltInRegistries.BLOCK.stream()
                .filter(reg -> Arrays.asList(replaceableBlocks).contains(Objects.requireNonNull(reg.defaultBlockState()
                                .getBlockHolder()
                                .getKey())
                        .location()
                        .getPath()))
                .map(BlockMatchTest::new)
                .map(rule -> OreConfiguration.target(rule, block.get().defaultBlockState()))
                .toList();
        ConfiguredFeatureUtils.register(context, configuredFeatureResourceKey, Feature.ORE, new OreConfiguration(rules, configuration.size()));
    }
}
