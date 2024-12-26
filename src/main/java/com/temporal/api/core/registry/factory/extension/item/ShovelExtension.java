package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ShovelExtension {
    default DeferredItem<ShovelItem> createShovel(String name, ToolMaterial material, float damage, float speed) {
        return this.createShovel(name, material, damage, speed, new Item.Properties());
    }

    default DeferredItem<ShovelItem> createShovel(String name, ToolMaterial material, float damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<ShovelItem>) itemFactory.createTyped(name, () -> new ShovelItem(material, damage, speed, properties));
    }

    default DeferredItem<? extends ShovelItem> createShovel(String name, Supplier<? extends ShovelItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<ShovelItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
