package com.temporal.api.core.registry.factory.other;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class ArmorMaterialFactory {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = InjectionContext.getFromInstance("armor_materials");

    private ArmorMaterialFactory() {
    }

    public static Holder<ArmorMaterial> create(String name, EnumMap<ArmorItem.Type, Integer> defenses,
                                               int enchantmentValue, float toughness, float knockbackResistance,
                                               TagKey<Item> repairIngredient, Holder<SoundEvent> equipSound) {
        ResourceLocation location = ResourceUtils.createResourceLocation(name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(repairIngredient);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
        EnumMap<ArmorItem.Type, Integer> map = Arrays.stream(ArmorItem.Type.values()).collect(Collectors.toMap(armoritem$type -> armoritem$type, defenses::get, (a, b) -> b, () -> new EnumMap<>(ArmorItem.Type.class)));
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(map, enchantmentValue, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
