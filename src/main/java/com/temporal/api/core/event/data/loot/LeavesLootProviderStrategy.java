package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class LeavesLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String saplingBlockId = (String) additionalData[0];
        Block saplingBlock = RegistryUtils.getBlockById(saplingBlockId);
        float[] chances = new float[additionalData.length - 1];
        for (int i = 1; i < additionalData.length; i++) {
            float chance = (float) additionalData[i];
            chances[i - 1] = chance;
        }

        provider.add(block, provider.createLeavesDrops(block, saplingBlock, chances));
    }
}
