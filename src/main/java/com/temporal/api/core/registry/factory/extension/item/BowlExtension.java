package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unchecked")
public interface BowlExtension {
    default RegistryObject<Item> createBowl(String name, int nutrition, float saturation) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        return (RegistryObject<Item>) itemFactory.createTyped(name, () -> new Item(new Item.Properties()
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationModifier(saturation)
                        .build())
                .usingConvertsTo(Items.BOWL)));
    }
}
