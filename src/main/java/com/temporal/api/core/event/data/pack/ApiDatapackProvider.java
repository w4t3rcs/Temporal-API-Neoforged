package com.temporal.api.core.event.data.pack;

import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.event.data.biome.CompoundGenerationProcessFacade;
import com.temporal.api.core.event.data.enchantment.ApiEnchantmentProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ApiDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ApiEnchantmentProvider::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, CompoundGenerationProcessFacade::executeConfiguredFeatures)
            .add(Registries.PLACED_FEATURE, CompoundGenerationProcessFacade::executePlacedFeatures)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CompoundGenerationProcessFacade::executeBiomeModifiers);

    public ApiDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(IOLayer.NEO_MOD.getModId()));
    }
}
