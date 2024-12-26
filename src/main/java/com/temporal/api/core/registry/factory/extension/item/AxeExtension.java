package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface AxeExtension {
    default DeferredItem<AxeItem> createAxe(String name, ToolMaterial material, float damage, float speed) {
        return this.createAxe(name, material, damage, speed, new Item.Properties());
    }

    default DeferredItem<AxeItem> createAxe(String name, ToolMaterial material, float damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<AxeItem>) itemFactory.createTyped(name, () -> new AxeItem(material, damage, speed, properties));
    }

    default DeferredItem<? extends AxeItem> createAxe(String name, Supplier<? extends AxeItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<AxeItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
