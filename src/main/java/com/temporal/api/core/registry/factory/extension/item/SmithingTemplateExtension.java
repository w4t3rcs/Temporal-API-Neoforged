package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SmithingTemplateExtension {
    default DeferredItem<SmithingTemplateItem> createSmithingTemplate(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<SmithingTemplateItem>) itemFactory.createTyped(name, () -> SmithingTemplateItem.createArmorTrimTemplate(properties));
    }

    default DeferredItem<? extends SmithingTemplateItem> createSmithingTemplate(String name, Supplier<? extends SmithingTemplateItem> tTypedSupplier) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return (DeferredItem<? extends SmithingTemplateItem>) itemFactory.createTyped(name, tTypedSupplier);
    }
}
