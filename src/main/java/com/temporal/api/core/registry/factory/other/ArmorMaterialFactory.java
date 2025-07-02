package com.temporal.api.core.registry.factory.other;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class ArmorMaterialFactory {
    private ArmorMaterialFactory() {
    }

    public static Holder<ArmorMaterial> create(String name, EnumMap<ArmorItem.Type, Integer> defenses,
                                               int enchantability, float toughness, float knockbackResistance,
                                               Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceUtils.createResourceLocation(name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(defenses, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
