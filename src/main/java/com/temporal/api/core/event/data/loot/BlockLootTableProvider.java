package com.temporal.api.core.event.data.loot;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockLootTableProvider extends ApiBlockLootTableProvider {
    public static final List<Holder<Block>> SELF = new ArrayList<>();
    public static final List<Holder<Block>> SILK_TOUCH = new ArrayList<>();
    public static final List<Holder<Block>> POTTED_CONTENT = new ArrayList<>();
    public static final Map<Holder<Block>, Holder<? extends ItemLike>> OTHER = new HashMap<>();
    public static final Map<DeferredBlock<?>, LootProviderStrategy> CUSTOM_LOOT = new HashMap<>();

    protected BlockLootTableProvider(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        SELF.forEach(blockRegistry -> this.dropSelf(blockRegistry.value()));
        SILK_TOUCH.forEach(blockRegistry -> this.dropWhenSilkTouch(blockRegistry.value()));
        POTTED_CONTENT.forEach(blockRegistry -> this.dropPottedContents(blockRegistry.value()));
        OTHER.forEach((blockRegistry, other) -> this.dropOther(blockRegistry.value(), other.value()));
        CUSTOM_LOOT.forEach((blockRegistry, strategy) -> strategy.generateLoot(blockRegistry, this));
    }
}
