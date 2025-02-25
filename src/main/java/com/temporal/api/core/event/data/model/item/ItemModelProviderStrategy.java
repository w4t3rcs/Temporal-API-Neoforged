package com.temporal.api.core.event.data.model.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ItemModelProviderStrategy<T extends Item> {
    void registerItemModel(DeferredItem<T> itemRegistry, Object... additionalData);
}
