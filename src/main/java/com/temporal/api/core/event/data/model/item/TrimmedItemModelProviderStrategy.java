package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.Map;

public class TrimmedItemModelProviderStrategy implements ItemModelProviderStrategy {
    public static final Map<String, ResourceKey<EquipmentAsset>> ASSETS = new HashMap<>();

    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        ResourceLocation id = itemRegistry.getId();
        String path = id.getPath();
        String armorType = "";
        if (path.contains("helmet")) armorType = "helmet";
        else if (path.contains("chestplate")) armorType = "chestplate";
        else if (path.contains("leggings")) armorType = "leggings";
        else if (path.contains("boots")) armorType = "boots";
        ResourceKey<EquipmentAsset> asset = ASSETS.entrySet()
                .stream()
                .filter(e -> path.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow();
        itemModels.generateTrimmableItem(itemRegistry.get(), asset, armorType, false);
    }
}
