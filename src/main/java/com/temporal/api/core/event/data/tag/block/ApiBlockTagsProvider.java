package com.temporal.api.core.event.data.tag.block;

import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiBlockTagsProvider extends BlockTagsProvider {
    public static final Map<String, BlockTagGenerationDescription> TAG_GENERATION_DESCRIPTIONS = new HashMap<>();

    public ApiBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, IOLayer.NEO_MOD.getModId());
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        TAG_GENERATION_DESCRIPTIONS.forEach((tag, description) -> {
            TagKey<Block> blockTag = IOHelper.<Block>getTagKeyStream(tag, description.tagContainer())
                    .findAny()
                    .orElseThrow();
            tag(blockTag).add(description.blocks()
                    .stream()
                    .map(DeferredHolder::get)
                    .toArray(Block[]::new));
        });
    }
}
