package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.event.handler.FMLClientSetupEventHandler;
import com.temporal.api.core.engine.event.handler.FovModifierEventHandler;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public interface BowExtension {
    default DeferredItem<Item> createBow(String name) {
        return createBow(name, new Item.Properties());
    }

    default DeferredItem<Item> createBow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        DeferredItem<Item> bow = itemFactory.create(name, properties.stacksTo(1), BowItem::new);
        FovModifierEventHandler.BOWS.add(bow);
        FMLClientSetupEventHandler.BOWS.add(bow);
        return bow;
    }
}
