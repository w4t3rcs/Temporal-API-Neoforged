package com.temporal.api.core.event.data;

import com.temporal.api.core.event.data.language.*;
import com.temporal.api.core.event.data.loot.LootTableProviderFactory;
import com.temporal.api.core.event.data.model.block.BlockStateProviderImpl;
import com.temporal.api.core.event.data.model.item.ItemModelProviderImpl;
import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ApiDataGenerator implements DataGatherer {
    @Override
    public void gatherData(GatherDataEvent event) {
        addLootTableProvider(event);
        addModelProvider(event);
        addLanguageProvider(event);
        addRecipeProvider(event);
    }

    @Override
    public void addLootTableProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(isServer(event), LootTableProviderFactory.createProvider(packOutput, lookupProvider));
    }

    @Override
    public void addModelProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        final ExistingFileHelper existingFileHelper = getExistingFileHelper(event);
        generator.addProvider(isClient(event), new BlockStateProviderImpl(packOutput, existingFileHelper));
        generator.addProvider(isClient(event), new ItemModelProviderImpl(packOutput, existingFileHelper));
    }

    @Override
    public void addLanguageProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        generator.addProvider(isClient(event), new EnglishProvider(packOutput));
        generator.addProvider(isClient(event), new UkrainianProvider(packOutput));
        generator.addProvider(isClient(event), new PolishProvider(packOutput));
        generator.addProvider(isClient(event), new GermanProvider(packOutput));
        generator.addProvider(isClient(event), new FrenchProvider(packOutput));
        generator.addProvider(isClient(event), new ItalianProvider(packOutput));
        generator.addProvider(isClient(event), new SpanishProvider(packOutput));
    }

    @Override
    public void addRecipeProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(isServer(event), new ApiRecipeProvider.Runner(packOutput, lookupProvider));
    }

    @Override
    public ExistingFileHelper getExistingFileHelper(GatherDataEvent event) {
        return event.getExistingFileHelper();
    }

    @Override
    public @NotNull PackOutput getPackOutput(GatherDataEvent event) {
        return getDataGenerator(event).getPackOutput();
    }

    @Override
    public DataGenerator getDataGenerator(GatherDataEvent event) {
        return event.getGenerator();
    }

    private boolean isServer(GatherDataEvent event) {
        return event instanceof GatherDataEvent.Server;
    }

    private boolean isClient(GatherDataEvent event) {
        return event instanceof GatherDataEvent.Client;
    }
}
