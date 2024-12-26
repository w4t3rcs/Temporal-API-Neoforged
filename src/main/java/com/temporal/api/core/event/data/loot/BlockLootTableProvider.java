package com.temporal.api.core.event.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockLootTableProvider extends ApiBlockLootTableProvider {
    public static final List<RegistryObject<Block>> SELF = new ArrayList<>();
    public static final List<RegistryObject<Block>> SILK_TOUCH = new ArrayList<>();
    public static final List<RegistryObject<Block>> POTTED_CONTENT = new ArrayList<>();
    public static final Map<RegistryObject<Block>, RegistryObject<? extends ItemLike>> OTHER = new HashMap<>();

    protected BlockLootTableProvider(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        SELF.forEach(blockRegistry -> this.dropSelf(blockRegistry.get()));
        SILK_TOUCH.forEach(blockRegistry -> this.dropWhenSilkTouch(blockRegistry.get()));
        POTTED_CONTENT.forEach(blockRegistry -> this.dropPottedContents(blockRegistry.get()));
        OTHER.forEach((blockRegistry, other) -> this.dropOther(blockRegistry.get(), other.get()));
    }
}
