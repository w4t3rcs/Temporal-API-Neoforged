package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ArrowExtension {
    default DeferredItem<Item> createArrow(String name) {
        return createArrow(name, new Item.Properties());
    }

    default DeferredItem<Item> createArrow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, ArrowItem::new);
    }
}
