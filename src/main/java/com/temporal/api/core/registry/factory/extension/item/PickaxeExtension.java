package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public interface PickaxeExtension {
    default DeferredItem<Item> createPickaxe(String name, ToolMaterial material, int damage, float speed) {
        return this.createPickaxe(name, material, damage, speed, new Item.Properties());
    }

    default DeferredItem<Item> createPickaxe(String name, ToolMaterial material, int damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new PickaxeItem(material, damage, speed, props));
    }
}
