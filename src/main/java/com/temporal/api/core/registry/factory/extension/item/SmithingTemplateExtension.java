package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SmithingTemplateExtension {
    default RegistryObject<SmithingTemplateItem> createSmithingTemplate(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<SmithingTemplateItem>) itemFactory.createTyped(name, () -> SmithingTemplateItem.createArmorTrimTemplate(properties));
    }

    default RegistryObject<? extends SmithingTemplateItem> createSmithingTemplate(String name, Supplier<? extends SmithingTemplateItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<? extends SmithingTemplateItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
