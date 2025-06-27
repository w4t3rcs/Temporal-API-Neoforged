package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemFactory implements ObjectFactory<Item> {
    public static final DeferredRegister.Items ITEMS = InjectionContext.getFromInstance("items");

    public DeferredItem<Item> create(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    public DeferredItem<Item> create(String name, Item.Properties properties) {
        return ITEMS.registerSimpleItem(name, properties);
    }

    public DeferredItem<Item> create(String name, Item.Properties properties, Function<Item.Properties, ? extends Item> function) {
        return ITEMS.registerItem(name, function, properties);
    }

    @Override
    public DeferredItem<Item> create(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    @Override
    public void register() {
        ITEMS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
