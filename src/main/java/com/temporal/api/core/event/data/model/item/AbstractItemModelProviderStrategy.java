package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

public abstract class AbstractItemModelProviderStrategy<T extends Item> implements ItemModelProviderStrategy<T> {
    private final ItemModelGenerators itemModels;

    protected AbstractItemModelProviderStrategy(ItemModelGenerators itemModels) {
        this.itemModels = itemModels;
    }

    public ItemModelGenerators getItemModels() {
        return itemModels;
    }
}
