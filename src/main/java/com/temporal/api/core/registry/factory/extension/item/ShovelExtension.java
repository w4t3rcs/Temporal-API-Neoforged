package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ShovelExtension {
    default DeferredItem<Item> createShovel(String name, ToolMaterial material, float damage, float speed) {
        return this.createShovel(name, new Item.Properties(), material, damage, speed);
    }

    default DeferredItem<Item> createShovel(String name, Item.Properties properties, ToolMaterial material, float damage, float speed) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new ShovelItem(material, damage, speed, props));
    }
}
