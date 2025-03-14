package com.temporal.api.core.registry.factory.other;

import com.temporal.api.core.event.data.model.item.TrimmedItemModelProviderStrategy;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public final class ArmorMaterialFactory {
    private ArmorMaterialFactory() {
    }

    public static ArmorMaterial create(String name, Map<ArmorType, Integer> defenses, int durability, int enchantmentValue, float toughness, float knockbackResistance, TagKey<Item> repairItem, Holder<SoundEvent> soundEvent, boolean isDatagenNeeded) {
        ResourceKey<Registry<EquipmentAsset>> root = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));
        ResourceLocation resourceLocation = ResourceUtils.createResourceLocation(name);
        ResourceKey<EquipmentAsset> asset = ResourceKey.create(root, resourceLocation);
        if (isDatagenNeeded) TrimmedItemModelProviderStrategy.ASSETS.put(name, asset);
        return new ArmorMaterial(durability, defenses, enchantmentValue, soundEvent, toughness, knockbackResistance, repairItem, asset);
    }
}
