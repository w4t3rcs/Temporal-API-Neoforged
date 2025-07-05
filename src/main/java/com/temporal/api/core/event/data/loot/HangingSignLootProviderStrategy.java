package com.temporal.api.core.event.data.loot;

import net.neoforged.neoforge.registries.DeferredBlock;

public class HangingSignLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        provider.dropSharedSelf(blockRegistry, id -> id.replace("hanging", "wall_hanging"));
    }
}
