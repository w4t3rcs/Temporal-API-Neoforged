package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ItemModelProviderStrategy {
    void registerItemModel(DeferredItem<?> itemRegistry, ItemModelGenerators itemModels, Object... additionalData);
}
