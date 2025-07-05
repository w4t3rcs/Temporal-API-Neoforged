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
    public static final Map<DeferredBlock<?>, Object[]> POTTED_CONTENT = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SIGN = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> HANGING_SIGN = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> OTHER = new TemporalMap<>();
    public static final Map<Pair<DeferredBlock<?>, Object[]>, LootProviderStrategy> CUSTOM_LOOT = new TemporalMap<>();

    protected BlockLootTableProvider(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        SELF.forEach(generateLootTable(this, SelfLootProviderStrategy::new));
        SILK_TOUCH.forEach(generateLootTable(this, SilkTouchLootProviderStrategy::new));
        POTTED_CONTENT.forEach(generateLootTable(this, PottedContentLootProviderStrategy::new));
        SIGN.forEach(generateLootTable(this, SignLootProviderStrategy::new));
        HANGING_SIGN.forEach(generateLootTable(this, HangingSignLootProviderStrategy::new));
        OTHER.forEach(generateLootTable(this, OtherLootProviderStrategy::new));
        CUSTOM_LOOT.forEach((key, strategy) -> strategy.generateLoot(key.getLeft(), this, key.getRight()));
    }

    @Override
    protected BiConsumer<DeferredBlock<?>, Object[]> generateLootTable(@NotNull ApiBlockLootTableProvider provider, @NotNull Supplier<LootProviderStrategy> lootProviderStrategySupplier) {
        return (blockRegistry, additionalData) -> lootProviderStrategySupplier.get().generateLoot(blockRegistry, provider, additionalData);
    }
}
