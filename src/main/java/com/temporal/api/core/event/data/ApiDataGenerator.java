package com.temporal.api.core.event.data;

import com.temporal.api.core.event.data.language.*;
import com.temporal.api.core.event.data.loot.LootTableProviderFactory;
import com.temporal.api.core.event.data.model.block.BlockStateProvider;
import com.temporal.api.core.event.data.model.item.ItemModelProvider;
import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
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
        generator.addProvider(event.includeServer(), LootTableProviderFactory.createProvider(packOutput, lookupProvider));
    }

    @Override
    public void addModelProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        final ExistingFileHelper existingFileHelper = getExistingFileHelper(event);
        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, existingFileHelper));
    }

    @Override
    public void addLanguageProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        generator.addProvider(event.includeClient(), new EnglishProvider(packOutput));
        generator.addProvider(event.includeClient(), new UkrainianProvider(packOutput));
        generator.addProvider(event.includeClient(), new PolishProvider(packOutput));
        generator.addProvider(event.includeClient(), new GermanProvider(packOutput));
        generator.addProvider(event.includeClient(), new FrenchProvider(packOutput));
        generator.addProvider(event.includeClient(), new ItalianProvider(packOutput));
        generator.addProvider(event.includeClient(), new SpanishProvider(packOutput));
    }

    @Override
    public void addRecipeProvider(GatherDataEvent event) {
        final DataGenerator generator = getDataGenerator(event);
        final PackOutput packOutput = getPackOutput(event);
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new ApiRecipeProvider.Runner(packOutput, lookupProvider));
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
