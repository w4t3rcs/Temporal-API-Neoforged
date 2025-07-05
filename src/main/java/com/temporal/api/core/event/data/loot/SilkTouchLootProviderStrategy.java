package com.temporal.api.core.event.data.loot;

import net.neoforged.neoforge.registries.DeferredBlock;

public class SilkTouchLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        provider.dropWhenSilkTouch(blockRegistry.value());
    }
}
