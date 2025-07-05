package com.temporal.api.core.event.data.loot;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ApiBlockLootTableProvider extends BlockLootSubProvider {
    protected ApiBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    protected abstract BiConsumer<DeferredBlock<?>, Object[]> generateLootTable(@NotNull ApiBlockLootTableProvider provider, @NotNull Supplier<LootProviderStrategy> lootProviderStrategySupplier);

    public void dropSharedSelf(DeferredBlock<?> block, Function<String, String> mapper) {
        String id = Objects.requireNonNull(block.getKey()).location().getPath();
        Item item = RegistryUtils.getItemById(id);
        Block mainBlock = block.value();
        Block sharedBlock = RegistryUtils.getBlockById(mapper.apply(id));
        this.dropOther(mainBlock, item);
        this.dropOther(sharedBlock, item);
    }

    @Override
    public void dropPottedContents(@NotNull Block flowerPot) {
        super.dropPottedContents(flowerPot);
    }

    @Override
    public void dropOther(@NotNull Block block, @NotNull ItemLike item) {
        super.dropOther(block, item);
    }

    @Override
    public void dropWhenSilkTouch(@NotNull Block block) {
        super.dropWhenSilkTouch(block);
    }

    @Override
    public void dropSelf(@NotNull Block block) {
        super.dropSelf(block);
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return InjectionPool.<DeferredRegister.Blocks>getFromInstance("$Blocks")
                .getEntries()
                .stream()
                .map(holder -> (Block) holder.get())
                .toList();
    }
}