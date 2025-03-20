package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.Queue;

public class BlockLootTableProvider extends ApiBlockLootTableProvider {
    public static final Queue<Holder<Block>> SELF = new TemporalArrayDeque<>();
    public static final Queue<Holder<Block>> SILK_TOUCH = new TemporalArrayDeque<>();
    public static final Queue<Holder<Block>> POTTED_CONTENT = new TemporalArrayDeque<>();
    public static final Map<Holder<Block>, Holder<? extends ItemLike>> OTHER = new TemporalHashMap<>();
    public static final Map<DeferredBlock<?>, LootProviderStrategy> CUSTOM_LOOT = new TemporalHashMap<>();

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
