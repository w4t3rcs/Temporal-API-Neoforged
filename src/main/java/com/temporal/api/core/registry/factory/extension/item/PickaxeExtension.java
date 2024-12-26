package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface PickaxeExtension {
    default RegistryObject<PickaxeItem> createPickaxe(String name, ToolMaterial material, int damage, float speed) {
        return this.createPickaxe(name, material, damage, speed, new Item.Properties());
    }

    default RegistryObject<PickaxeItem> createPickaxe(String name, ToolMaterial material, int damage, float speed, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<PickaxeItem>) itemFactory.createTyped(name, () -> new PickaxeItem(material, damage, speed, properties));
    }

    default RegistryObject<? extends PickaxeItem> createPickaxe(String name, Supplier<? extends PickaxeItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<PickaxeItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
