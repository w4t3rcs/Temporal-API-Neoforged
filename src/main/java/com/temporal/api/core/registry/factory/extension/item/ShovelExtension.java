package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;

public interface ShovelExtension {
    default DeferredItem<Item> createShovel(String name, Tier tier) {
        return this.createShovel(name, new Item.Properties(), tier);
    }

    default DeferredItem<Item> createShovel(String name, Item.Properties properties, Tier tier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new ShovelItem(tier, props));
    }
}
