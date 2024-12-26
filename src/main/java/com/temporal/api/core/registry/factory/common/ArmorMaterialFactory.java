package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
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

//The main reason of this class breaking factory hierarchy: there is no built-in registry for ArmorMaterial
public class ArmorMaterialFactory {
    public ArmorMaterial create(String name, Map<ArmorType, Integer> defenses, int durability, int enchantmentValue, float toughness, float knockbackResistance, TagKey<Item> repairItem, Holder<SoundEvent> soundEvent) {
        ResourceKey<Registry<EquipmentAsset>> root = ResourceKey.createRegistryKey(IOHelper.createResourceLocation("equipment_asset"));
        ResourceLocation resourceLocation = IOHelper.createResourceLocation(name);
        return new ArmorMaterial(durability, defenses, enchantmentValue, soundEvent, toughness, knockbackResistance, repairItem, ResourceKey.create(root, resourceLocation));
    }
}
