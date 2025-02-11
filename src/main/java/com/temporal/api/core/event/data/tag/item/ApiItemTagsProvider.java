package com.temporal.api.core.event.data.tag.item;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiItemTagsProvider extends ItemTagsProvider {
    public static final Map<String, List<DeferredItem<?>>> TAG_GENERATION_DESCRIPTIONS = new HashMap<>();

    public ApiItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, IOLayer.NEO_MOD.getModId());
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        TAG_GENERATION_DESCRIPTIONS.forEach((tag, items) -> {
            if (ItemTagGenerationPreparer.ITEM_TAGS.containsKey(tag)) {
                TagKey<Item> tagKey = ItemTagGenerationPreparer.ITEM_TAGS.get(tag);
                tag(tagKey).add(items.stream()
                        .map(DeferredHolder::get)
                        .toArray(Item[]::new));
            }
        });
    }
}
