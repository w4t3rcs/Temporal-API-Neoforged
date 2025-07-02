package com.temporal.api.core.event.data.model.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class PotionItemModelProviderStrategy implements ItemModelProviderStrategy {
    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ApiItemModelProvider provider, Object... additionalData) {
        Item item = itemRegistry.get();
        String itemPath = provider.getItemPath(item);
        provider.withExistingParent(itemPath, "item/potion")
                .texture("layer0", provider.mcLoc("item/potion_overlay"))
                .texture("layer1", ResourceLocation.parse(itemPath));
    }
}
