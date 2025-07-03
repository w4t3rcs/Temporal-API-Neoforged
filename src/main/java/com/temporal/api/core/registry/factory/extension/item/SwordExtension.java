package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;

public interface SwordExtension {
    default DeferredItem<Item> createSword(String name, Tier tier, int damage, float speed) {
        return this.createSword(name, new Item.Properties(), tier, damage, speed);
    }

    default DeferredItem<Item> createSword(String name, Item.Properties properties, Tier tier, int damage, float speed) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties.attributes(SwordItem.createAttributes(tier, damage, speed)), props -> new SwordItem(tier, props));
    }
}
