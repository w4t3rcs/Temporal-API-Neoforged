package com.temporal.api.core.event.data.model.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class BowModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ApiItemModelProvider provider, Object... additionalData) {
        Item item = itemRegistry.get();
        String itemPath = provider.getItemPath(item);
        provider.simpleItem(itemPath, "bow")
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .model(provider.simpleItem(itemPath + "_pulling_0", "bow"))
                .end()
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .predicate(provider.mcLoc("pull"), 0.65f)
                .model(provider.simpleItem(itemPath + "_pulling_1", "bow"))
                .end()
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .predicate(provider.mcLoc("pull"), 0.9f)
                .model(provider.simpleItem(itemPath + "_pulling_2", "bow"))
                .end();
    }
}
