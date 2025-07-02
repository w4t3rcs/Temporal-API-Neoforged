package com.temporal.api.core.event.data.model.item;

import net.neoforged.neoforge.registries.DeferredItem;

public interface ItemModelProviderStrategy {
    void registerItemModel(DeferredItem<?> itemRegistry, ApiItemModelProvider provider, Object... additionalData);
}
