package com.temporal.api.core.event.data.loot;

import net.neoforged.neoforge.registries.DeferredBlock;

public class SelfLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        provider.dropSelf(blockRegistry.value());
    }
}
