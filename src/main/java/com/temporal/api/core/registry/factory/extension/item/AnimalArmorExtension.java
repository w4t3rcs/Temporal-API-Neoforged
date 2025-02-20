package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public interface AnimalArmorExtension {
    default DeferredItem<Item> createAnimalArmor(String name, ArmorMaterial material, AnimalArmorItem.BodyType type) {
        return this.createAnimalArmor(name, material, type, new Item.Properties());
    }

    default DeferredItem<Item> createAnimalArmor(String name, ArmorMaterial material, AnimalArmorItem.BodyType type, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new AnimalArmorItem(material, type, props));
    }
}
