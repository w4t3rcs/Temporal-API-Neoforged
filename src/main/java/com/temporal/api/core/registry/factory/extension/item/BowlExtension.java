package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;

public interface BowlExtension {
    default DeferredItem<Item> createBowl(String name, int nutrition, float saturation) {
        return this.createBowl(name, new Item.Properties(), nutrition, saturation);
    }

    default DeferredItem<Item> createBowl(String name, Item.Properties properties, int nutrition, float saturation) {
        return this.createBowl(name, properties, new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build());
    }

    default DeferredItem<Item> createBowl(String name, Item.Properties properties, FoodProperties foodProperties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        return itemFactory.create(name, properties.stacksTo(1)
                .food(foodProperties)
                .usingConvertsTo(Items.BOWL), Item::new);
    }
}
