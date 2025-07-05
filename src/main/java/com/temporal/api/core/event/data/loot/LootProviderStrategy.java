package com.temporal.api.core.event.data.loot;

import net.neoforged.neoforge.registries.DeferredBlock;

public interface LootProviderStrategy {
    void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData);
}
