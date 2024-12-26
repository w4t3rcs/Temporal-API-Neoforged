package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemFactory implements TypedFactory<Item> {
    public static final DeferredRegister.Items ITEMS = IOHelper.createItemRegistry();

    public DeferredItem<Item> create(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    public DeferredItem<Item> create(String name, Item.Properties properties) {
        return ITEMS.registerSimpleItem(name, properties);
    }

    @Override
    public DeferredItem<Item> create(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    @Override
    public DeferredItem<? extends Item> createTyped(String name, Supplier<? extends Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    @Override
    public void register() {
        ITEMS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
