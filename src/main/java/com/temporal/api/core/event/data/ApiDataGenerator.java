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
        addRecipeProvider(event);
        addModelProvider(event);
        addLanguageProvider(event);
    }

    @Override
    public void addLootTableProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, LootTableProviderFactory.createProvider(packOutput, lookupProvider));
    }

    @Override
    public void addModelProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        final ExistingFileHelper existingFileHelper = getExistingFileHelper(event);
        generator.addProvider(true, new BlockStateProviderImpl(packOutput, existingFileHelper));
        generator.addProvider(true, new ItemModelProviderImpl(packOutput, existingFileHelper));
    }

    @Override
    public void addLanguageProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        generator.addProvider(true, new EnglishProvider(packOutput));
        generator.addProvider(true, new UkrainianProvider(packOutput));
        generator.addProvider(true, new PolishProvider(packOutput));
        generator.addProvider(true, new GermanProvider(packOutput));
        generator.addProvider(true, new FrenchProvider(packOutput));
        generator.addProvider(true, new ItalianProvider(packOutput));
        generator.addProvider(true, new SpanishProvider(packOutput));
    }

    @Override
    public void addRecipeProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, new ApiRecipeProvider.Runner(packOutput, lookupProvider));
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
}
