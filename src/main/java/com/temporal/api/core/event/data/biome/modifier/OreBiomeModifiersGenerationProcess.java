package com.temporal.api.core.event.data.biome.modifier;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.OreGeneration;
import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.placement.PlacedFeaturesContainer;
import com.temporal.api.core.util.feature.BiomeModifiersUtils;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OreBiomeModifiersGenerationProcess implements GenerationProcess<BiomeModifier, OreGeneration> {
    @Override
    public void bootstrap(BootstrapContext<BiomeModifier> context, DeferredBlock<?> block, OreGeneration description) {
        String registryName = description.configuration().registry();
        OreGeneration.BiomeModifier biomeModifier = description.biomeModifier();
        ResourceKey<BiomeModifier> biomeModifierKey = BiomeModifiersUtils.registerKey("add_" + registryName);
        BiomeModifiersContainer.BIOME_MODIFIERS.put(registryName, biomeModifierKey);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        String biomeTag = biomeModifier.biomeTag();
        var foundBiomes = IOHelper.getTagHoldersByKey(biomeTag, biomes, biomeModifier.biomeTagContainers());
        context.register(biomeModifierKey, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(foundBiomes),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesContainer.PLACED_FEATURES.get(registryName))),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }


}
