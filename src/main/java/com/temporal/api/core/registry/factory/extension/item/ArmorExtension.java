package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ArmorExtension {
    default DeferredItem<Item> createArmor(String name, ArmorMaterial material, ArmorType type) {
        return this.createArmor(name, new Item.Properties(), material, type);
    }

    default DeferredItem<Item> createArmor(String name, Item.Properties properties, ArmorMaterial material, ArmorType type) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new ArmorItem(material, type, props));
    }
}
