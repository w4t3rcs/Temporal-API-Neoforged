package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ShieldModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        Item item = itemRegistry.get();
        itemModels.generateShield(item);
    }
}
