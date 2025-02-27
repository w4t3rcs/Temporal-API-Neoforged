package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class BowModelProviderStrategy extends AbstractItemModelProviderStrategy<Item> {
    protected BowModelProviderStrategy(ItemModelGenerators itemModels) {
        super(itemModels);
    }

    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, Object... additionalData) {
        Item item = itemRegistry.get();
        this.getItemModels().generateBow(item);
    }
}
