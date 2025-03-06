package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class PotionItemModelProviderStrategy implements ItemModelProviderStrategy<Item> {
    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        Item item = itemRegistry.get();
        itemModels.generatePotion(item);
    }
}
