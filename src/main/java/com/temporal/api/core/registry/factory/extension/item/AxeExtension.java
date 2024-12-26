package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public interface AxeExtension {
    default DeferredItem<Item> createAxe(String name, ToolMaterial material, float damage, float speed) {
        return this.createAxe(name, material, damage, speed, new Item.Properties());
    }

    default DeferredItem<Item> createAxe(String name, ToolMaterial material, float damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new AxeItem(material, damage, speed, props));
    }
}
