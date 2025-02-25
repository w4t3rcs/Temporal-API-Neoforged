package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public interface AnimalArmorExtension {
    default DeferredItem<Item> createAnimalArmor(String name, ArmorMaterial material, AnimalArmorItem.BodyType type) {
        return this.createAnimalArmor(name, new Item.Properties(), material, type);
    }

    default DeferredItem<Item> createAnimalArmor(String name, Item.Properties properties, ArmorMaterial material, AnimalArmorItem.BodyType type) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new AnimalArmorItem(material, type, props));
    }
}
