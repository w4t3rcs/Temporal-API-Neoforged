package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.event.handler.FMLClientSetupEventHandler;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public interface CrossbowExtension {
    default DeferredItem<Item> createCrossbow(String name) {
        return createCrossbow(name, new Item.Properties());
    }

    default DeferredItem<Item> createCrossbow(String name, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        DeferredItem<Item> crossbow = itemFactory.create(name, properties.stacksTo(1), CrossbowItem::new);
        FMLClientSetupEventHandler.CROSSBOWS.add(crossbow);
        return crossbow;
    }
}
