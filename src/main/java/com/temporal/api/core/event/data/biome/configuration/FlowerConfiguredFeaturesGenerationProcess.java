package com.temporal.api.core.event.data.biome.configuration;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.dto.Flower;
import com.temporal.api.core.util.biome.ConfiguredFeatureUtils;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Arrays;

public class FlowerConfiguredFeaturesGenerationProcess implements GenerationProcess<ConfiguredFeature<?, ?>, Flower> {
    @Override
    public void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context, DeferredBlock<?> block, Flower description) {
        Flower.Configuration configuration = description.configuration();
        String registryName = description.id();
        var configuredFeatureResourceKey = ConfiguredFeatureUtils.createKey(registryName);
        ConfiguredFeaturesContainer.CONFIGURED_FEATURES.put(registryName, configuredFeatureResourceKey);
        ConfiguredFeatureUtils.register(context, configuredFeatureResourceKey, Feature.FLOWER,
                new RandomPatchConfiguration(configuration.tries(), configuration.xzSpread(), configuration.ySpread(),
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new NoiseThresholdProvider(configuration.noiseSeed(),
                                                new NormalNoise.NoiseParameters(configuration.firstOctave(), Arrays.stream(configuration.amplitudes()).boxed().toList()),
                                                configuration.noiseScale(),
                                                configuration.noiseThreshold(),
                                                configuration.noiseHighChance(),
                                                block.get().defaultBlockState(),
                                                Arrays.stream(configuration.lowStateFlowers())
                                                        .map(IOHelper::getBlockById)
                                                        .map(Block::defaultBlockState)
                                                        .toList(),
                                                Arrays.stream(configuration.highStateFlowers())
                                                        .map(IOHelper::getBlockById)
                                                        .map(Block::defaultBlockState)
                                                        .toList())))));
    }
}
