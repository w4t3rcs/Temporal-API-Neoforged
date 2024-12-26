package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ArrowExtension {
    default RegistryObject<ArrowItem> createArrow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<ArrowItem>) itemFactory.createTyped(name, () -> new ArrowItem(properties));
    }

    default RegistryObject<? extends ArrowItem> createArrow(String name, Supplier<? extends ArrowItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<? extends ArrowItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
