package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class HandheldItemModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        Item item = itemRegistry.get();
        itemModels.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
