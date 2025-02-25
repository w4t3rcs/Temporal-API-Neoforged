package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ShieldModelProviderStrategy extends AbstractItemModelProviderStrategy<Item> {
    protected ShieldModelProviderStrategy(ItemModelGenerators itemModels) {
        super(itemModels);
    }

    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, Object... additionalData) {
        Item item = itemRegistry.get();
        this.getItemModels().generateShield(item);
    }
}
