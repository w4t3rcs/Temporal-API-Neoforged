package com.temporal.api.core.event.data.model.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class HandheldItemModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ApiItemModelProvider provider, Object... additionalData) {
        Item item = itemRegistry.get();
        provider.handheldItem(item);
    }
}
