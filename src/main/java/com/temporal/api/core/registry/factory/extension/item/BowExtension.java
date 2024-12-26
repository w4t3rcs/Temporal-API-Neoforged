package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface BowExtension {
    default RegistryObject<BowItem> createBow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<BowItem>) itemFactory.createTyped(name, () -> new BowItem(properties));
    }

    default RegistryObject<? extends BowItem> createBow(String name, Supplier<? extends BowItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<? extends BowItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
