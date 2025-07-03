package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemFactory implements ObjectFactory<Item> {
    private final DeferredRegister.Items items;

    public ItemFactory() {
        this(InjectionContext.getFromInstance("items"));
    }

    public ItemFactory(DeferredRegister.Items items) {
        this.items = items;
    }

    public DeferredItem<Item> create(String name) {
        return items.registerSimpleItem(name);
    }

    public DeferredItem<Item> create(String name, Item.Properties properties) {
        return items.registerSimpleItem(name, properties);
    }

    public DeferredItem<Item> create(String name, Item.Properties properties, Function<Item.Properties, ? extends Item> function) {
        return items.registerItem(name, function, properties);
    }

    @Override
    public DeferredItem<Item> create(String name, Supplier<Item> itemSupplier) {
        return items.register(name, itemSupplier);
    }

    @Override
    public DeferredRegister<Item> getRegistry() {
        return items;
    }
}
