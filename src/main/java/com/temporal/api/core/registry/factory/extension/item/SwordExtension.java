package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SwordExtension {
    default DeferredItem<SwordItem> createSword(String name, ToolMaterial material, int damage, float speed) {
        return this.createSword(name, material, damage, speed, new Item.Properties());
    }

    default DeferredItem<SwordItem> createSword(String name, ToolMaterial material, int damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<SwordItem>) itemFactory.createTyped(name, () -> new SwordItem(material, damage, speed, properties));
    }

    default DeferredItem<? extends SwordItem> createSword(String name, Supplier<? extends SwordItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<SwordItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
