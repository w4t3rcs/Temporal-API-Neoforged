package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ArrowExtension {
    default DeferredItem<ArrowItem> createArrow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<ArrowItem>) itemFactory.createTyped(name, () -> new ArrowItem(properties));
    }

    default DeferredItem<? extends ArrowItem> createArrow(String name, Supplier<? extends ArrowItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<? extends ArrowItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
