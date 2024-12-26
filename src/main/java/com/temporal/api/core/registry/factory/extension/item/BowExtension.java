package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface BowExtension {
    default DeferredItem<BowItem> createBow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<BowItem>) itemFactory.createTyped(name, () -> new BowItem(properties));
    }

    default DeferredItem<? extends BowItem> createBow(String name, Supplier<? extends BowItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<? extends BowItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
