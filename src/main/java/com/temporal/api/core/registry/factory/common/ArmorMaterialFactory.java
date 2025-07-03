package com.temporal.api.core.registry.factory.common;

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

public class ArmorMaterialFactory implements ObjectFactory<ArmorMaterial> {
    private final DeferredRegister<ArmorMaterial> armorMaterials;

    public ArmorMaterialFactory() {
        this(InjectionContext.getFromInstance("armor_materials"));
    }

    public ArmorMaterialFactory(DeferredRegister<ArmorMaterial> armorMaterials) {
        this.armorMaterials = armorMaterials;
    }

    public Holder<ArmorMaterial> create(String name, EnumMap<ArmorItem.Type, Integer> defenses,
                                               int enchantmentValue, float toughness, float knockbackResistance,
                                               TagKey<Item> repairIngredient, Holder<SoundEvent> equipSound) {
        ResourceLocation location = ResourceUtils.createResourceLocation(name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(repairIngredient);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
        EnumMap<ArmorItem.Type, Integer> map = Arrays.stream(ArmorItem.Type.values()).collect(Collectors.toMap(armoritem$type -> armoritem$type, defenses::get, (a, b) -> b, () -> new EnumMap<>(ArmorItem.Type.class)));
        return this.create(name, () -> new ArmorMaterial(map, enchantmentValue, equipSound, ingredient, layers, toughness, knockbackResistance));
    }

    @Override
    public Holder<ArmorMaterial> create(String name, Supplier<ArmorMaterial> armorMaterialSupplier) {
        return armorMaterials.register(name, armorMaterialSupplier);
    }

    @Override
    public DeferredRegister<ArmorMaterial> getRegistry() {
        return armorMaterials;
    }
}
