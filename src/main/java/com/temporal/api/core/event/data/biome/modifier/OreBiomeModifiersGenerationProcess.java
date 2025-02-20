package com.temporal.api.core.event.data.biome.modifier;

import com.temporal.api.core.event.data.biome.GenerationProcess;
import com.temporal.api.core.event.data.biome.dto.Ore;
import com.temporal.api.core.event.data.biome.placement.PlacedFeaturesContainer;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import com.temporal.api.core.util.biome.BiomeModifiersUtils;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OreBiomeModifiersGenerationProcess implements GenerationProcess<BiomeModifier, Ore> {
    @Override
    public void bootstrap(BootstrapContext<BiomeModifier> context, DeferredBlock<?> block, Ore description) {
        String registryName = description.id();
        Ore.BiomeModifier biomeModifier = description.biomeModifier();
        ResourceKey<BiomeModifier> biomeModifierKey = BiomeModifiersUtils.registerKey("add_" + registryName);
        BiomeModifiersContainer.BIOME_MODIFIERS.put(registryName, biomeModifierKey);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        String biomeTag = biomeModifier.biomeTag();
        HolderSet.Named<Biome> foundBiomes = biomes.getOrThrow(BiomeTagDynamicPreparer.BIOME_TAGS.get(biomeTag));
        context.register(biomeModifierKey, new BiomeModifiers.AddFeaturesBiomeModifier(
                foundBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesContainer.PLACED_FEATURES.get(registryName))),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }


}
