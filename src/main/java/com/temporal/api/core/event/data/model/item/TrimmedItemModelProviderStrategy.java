package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.event.data.preparer.resource.equipment.EquipmentResourceDynamicPreparer;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public class TrimmedItemModelProviderStrategy implements ItemModelProviderStrategy<Item> {
    @Override
    public void registerItemModel(DeferredItem<Item> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        ResourceLocation id = itemRegistry.getId();
        String path = id.getPath();
        String armorType = "";
        if (path.contains("helmet")) armorType = "helmet";
        else if (path.contains("chestplate")) armorType = "chestplate";
        else if (path.contains("leggings")) armorType = "leggings";
        else if (path.contains("boots")) armorType = "boots";
        ResourceKey<EquipmentAsset> assetResourceKey = EquipmentResourceDynamicPreparer.EQUIPMENT_ASSET_RESOURCES.entrySet()
                .stream()
                .filter(e -> path.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow();
        itemModels.generateTrimmableItem(itemRegistry.get(), assetResourceKey, armorType, false);
    }
}
