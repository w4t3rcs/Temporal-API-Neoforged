package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class BasicItemModelProviderStrategy extends AbstractItemModelProviderStrategy<Item> {
    protected BasicItemModelProviderStrategy(ItemModelGenerators itemModels) {
        super(itemModels);
    }

    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry) {
        Item item = itemRegistry.get();
        this.getItemModels().generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
}
