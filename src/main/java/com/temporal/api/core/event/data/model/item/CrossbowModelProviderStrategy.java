package com.temporal.api.core.event.data.model.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class CrossbowModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ApiItemModelProvider provider, Object... additionalData) {
        Item item = itemRegistry.get();
        String itemPath = provider.getItemPath(item);
        provider.simpleItem(itemPath, "crossbow")
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .model(provider.simpleItem(itemPath + "_pulling_0", "crossbow"))
                .end()
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .predicate(provider.mcLoc("pull"), 0.58f)
                .model(provider.simpleItem(itemPath + "_pulling_1", "crossbow"))
                .end()
                .override()
                .predicate(provider.mcLoc("pulling"), 1)
                .predicate(provider.mcLoc("pull"), 1f)
                .model(provider.simpleItem(itemPath + "_pulling_2", "crossbow"))
                .end()
                .override()
                .predicate(provider.mcLoc("charged"), 1)
                .model(provider.simpleItem(itemPath + "_arrow", "crossbow"))
                .end()
                .override()
                .predicate(provider.mcLoc("charged"), 1)
                .predicate(provider.mcLoc("firework"), 1)
                .model(provider.simpleItem(itemPath + "_firework", "crossbow"))
                .end();
    }
}
