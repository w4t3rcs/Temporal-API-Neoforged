package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class BowModelProviderStrategy implements ItemModelProviderStrategy<Item> {
    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        Item item = itemRegistry.get();
        ItemModel.Unbaked itemModel = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.BOW));
        ItemModel.Unbaked pullingBow0 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_0", ModelTemplates.BOW));
        ItemModel.Unbaked pullingBow1 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_1", ModelTemplates.BOW));
        ItemModel.Unbaked pullingBow2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_2", ModelTemplates.BOW));
        itemModels.itemModelOutput.accept(item, ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), ItemModelUtils.rangeSelect(
                new UseDuration(false), 0.05F, pullingBow0,
                ItemModelUtils.override(pullingBow1, 0.65F),
                ItemModelUtils.override(pullingBow2, 0.9F)), itemModel)
        );
    }
}
