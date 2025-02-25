package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public interface SpawnEggExtension {
    default DeferredItem<Item> createSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> type) {
        return createSpawnEgg(name, new Item.Properties(), type);
    }

    default DeferredItem<Item> createSpawnEgg(String name, Item.Properties properties, Supplier<? extends EntityType<? extends Mob>> type) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, props -> new SpawnEggItem(type.get(), props));
    }
}
