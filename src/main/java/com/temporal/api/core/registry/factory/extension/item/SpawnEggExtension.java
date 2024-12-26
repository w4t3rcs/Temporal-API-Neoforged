package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SpawnEggExtension {
    default RegistryObject<SpawnEggItem> createSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> type, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<SpawnEggItem>) itemFactory.createTyped(name, () -> new SpawnEggItem(type.get(), properties));
    }

    default RegistryObject<? extends SpawnEggItem> createSpawnEgg(String name, Supplier<? extends SpawnEggItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<? extends SpawnEggItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
