package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;

public interface AxeExtension {
    default DeferredItem<Item> createAxe(String name, Tier tier) {
        return this.createAxe(name, new Item.Properties(), tier);
    }

    default DeferredItem<Item> createAxe(String name, Item.Properties properties, Tier tier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new AxeItem(tier, props));
    }
}
