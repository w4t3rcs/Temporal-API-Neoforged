package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OtherLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        Block block = blockRegistry.value();
        String otherId = additionalData[0].toString();
        Item item = RegistryUtils.getItemById(otherId);
        provider.dropOther(block, item);
    }
}
