package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class ApiBlockLootTableProvider extends BlockLootSubProvider {
    protected ApiBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return BlockFactory.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }
}
