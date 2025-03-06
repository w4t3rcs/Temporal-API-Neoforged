package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CrossbowPull;
import net.minecraft.client.renderer.item.properties.select.Charge;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class CrossbowModelProviderStrategy implements ItemModelProviderStrategy<Item> {
    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        Item item = itemRegistry.get();
        ItemModel.Unbaked crossbowModel = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.CROSSBOW));
        ItemModel.Unbaked crossbowPulling0 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_0", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked crossbowPulling1 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_1", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked crossbowPulling2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_2", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked crossbowArrow = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_arrow", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked crossbowFirework = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_firework", ModelTemplates.CROSSBOW));
        itemModels.itemModelOutput.accept(item, ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), ItemModelUtils.rangeSelect(
                new CrossbowPull(), crossbowPulling0,
                ItemModelUtils.override(crossbowPulling1, 0.58F),
                ItemModelUtils.override(crossbowPulling2, 1.0F)),
                ItemModelUtils.select(new Charge(), crossbowModel,
                        ItemModelUtils.when(CrossbowItem.ChargeType.ARROW, crossbowArrow),
                        ItemModelUtils.when(CrossbowItem.ChargeType.ROCKET, crossbowFirework))
                )
        );
    }
}
