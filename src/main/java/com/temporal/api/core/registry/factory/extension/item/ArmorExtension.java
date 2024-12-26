package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ArmorExtension {
    default DeferredItem<ArmorItem> createArmor(String name, ArmorMaterial material, ArmorType type) {
        return this.createArmor(name, material, type, new Item.Properties());
    }

    default DeferredItem<ArmorItem> createArmor(String name, ArmorMaterial material, ArmorType type, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<ArmorItem>) itemFactory.createTyped(name, () -> new ArmorItem(material, type, properties));
    }

    default DeferredItem<? extends ArmorItem> createArmor(String name, Supplier<? extends ArrowItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<? extends ArmorItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
