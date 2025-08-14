package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.collection.Pair;
import com.temporal.api.core.collection.TemporalMap;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BlockLootTableProvider extends ApiBlockLootTableProvider {
    public static final Map<DeferredBlock<?>, Object[]> SELF = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SILK_TOUCH = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> POTTED_CONTENTS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> ORES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> MULTIPLE_ORES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> GRASSES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> LEAVES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SHULKER_BOXES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> BANNERS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> MUSHROOM_BLOCKS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SHEARS_ONLY = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> CROPS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> DOORS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> OTHER = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> EMPTY = new TemporalMap<>();
    public static final Map<Pair<DeferredBlock<?>, Object[]>, LootProviderStrategy> CUSTOM_LOOT = new TemporalMap<>();

    protected BlockLootTableProvider(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        SELF.forEach(generateLootTable(this, SelfLootProviderStrategy::new));
        SILK_TOUCH.forEach(generateLootTable(this, SilkTouchLootProviderStrategy::new));
        POTTED_CONTENTS.forEach(generateLootTable(this, PottedContentLootProviderStrategy::new));
        ORES.forEach(generateLootTable(this, OreLootProviderStrategy::new));
        MULTIPLE_ORES.forEach(generateLootTable(this, MultipleOreLootProviderStrategy::new));
        GRASSES.forEach(generateLootTable(this, GrassLootProviderStrategy::new));
        LEAVES.forEach(generateLootTable(this, LeavesLootProviderStrategy::new));
        SHULKER_BOXES.forEach(generateLootTable(this, ShulkerBoxLootProviderStrategy::new));
        BANNERS.forEach(generateLootTable(this, BannerLootProviderStrategy::new));
        MUSHROOM_BLOCKS.forEach(generateLootTable(this, MushroomBlockLootProviderStrategy::new));
        SHEARS_ONLY.forEach(generateLootTable(this, ShearsOnlyLootProviderStrategy::new));
        CROPS.forEach(generateLootTable(this, CropLootProviderStrategy::new));
        DOORS.forEach(generateLootTable(this, DoorLootProviderStrategy::new));
        OTHER.forEach(generateLootTable(this, OtherLootProviderStrategy::new));
        EMPTY.forEach(generateLootTable(this, EmptyLootProviderStrategy::new));
        CUSTOM_LOOT.forEach((key, strategy) -> strategy.generateLoot(key.getLeft(), this, key.getRight()));
    }

    @Override
    protected BiConsumer<DeferredBlock<?>, Object[]> generateLootTable(@NotNull ApiBlockLootTableProvider provider, @NotNull Supplier<LootProviderStrategy> lootProviderStrategySupplier) {
        return (blockRegistry, additionalData) -> lootProviderStrategySupplier.get().generateLoot(blockRegistry, provider, additionalData);
    }
}
