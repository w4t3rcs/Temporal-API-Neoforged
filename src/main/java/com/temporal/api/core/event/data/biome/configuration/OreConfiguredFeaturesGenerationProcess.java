package com.temporal.api.core.event.data.biome.configuration;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.dto.Ore;
import com.temporal.api.core.util.biome.ConfiguredFeatureUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Arrays;

public class OreConfiguredFeaturesGenerationProcess implements GenerationProcess<ConfiguredFeature<?, ?>, Ore> {
    @Override
    public void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context, DeferredBlock<?> block, Ore description) {
        Ore.Configuration configuration = description.configuration();
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeatureUtils.createKey(registryName);
        ConfiguredFeaturesContainer.CONFIGURED_FEATURES.put(registryName, configuredFeatureResourceKey);
        String[] replaceableBlocks = configuration.replaceableBlocks();
        var rules = BuiltInRegistries.BLOCK.stream()
                .filter(reg -> Arrays.asList(replaceableBlocks).contains(IOHelper.getIdFromBlock(reg)))
                .map(BlockMatchTest::new)
                .map(rule -> OreConfiguration.target(rule, block.get().defaultBlockState()))
                .toList();
        ConfiguredFeatureUtils.register(context, configuredFeatureResourceKey, Feature.ORE, new OreConfiguration(rules, configuration.size()));
    }
}
