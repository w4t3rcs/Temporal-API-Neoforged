package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CropLootProviderStrategy implements LootProviderStrategy {
    @Override
    public void generateLoot(DeferredBlock<?> blockRegistry, ApiBlockLootTableProvider provider, Object... additionalData) {
        Block block = blockRegistry.get();
        String grownItemId = (String) additionalData[0];
        Item grownItem = RegistryUtils.getItemById(grownItemId);
        String seedsItemId = (String) additionalData[1];
        Item seedsItem = RegistryUtils.getItemById(seedsItemId);
        int grownAge = (int) additionalData[2];
        int minAge = (int) additionalData[3];
        int maxAge = (int) additionalData[4];
        provider.add(block, provider.createCropDrops(block, grownItem, seedsItem, LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(IntegerProperty.create("age", minAge, maxAge), grownAge))));
    }
}
