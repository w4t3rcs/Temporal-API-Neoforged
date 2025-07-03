package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;

public interface AxeExtension {
    default DeferredItem<Item> createAxe(String name, Tier tier, float damage, float speed) {
        return this.createAxe(name, new Item.Properties(), tier, damage, speed);
    }

    default DeferredItem<Item> createAxe(String name, Item.Properties properties, Tier tier, float damage, float speed) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties.attributes(AxeItem.createAttributes(tier, damage, speed)), props -> new AxeItem(tier, props));
    }
}
