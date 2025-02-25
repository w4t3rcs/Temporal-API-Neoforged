package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.neoforge.registries.DeferredItem;

public interface SmithingTemplateExtension {
    default DeferredItem<Item> createSmithingTemplate(String name) {
        return createSmithingTemplate(name, new Item.Properties());
    }

    default DeferredItem<Item> createSmithingTemplate(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties, SmithingTemplateItem::createArmorTrimTemplate);
    }
}
