package com.temporal.api.core.event.data.loot;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ShearsOnlyLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        provider.add(block, provider.createShearsDispatchTable(block, provider.applyExplosionDecay(block, LootItem.lootTableItem(block))));
    }
}
