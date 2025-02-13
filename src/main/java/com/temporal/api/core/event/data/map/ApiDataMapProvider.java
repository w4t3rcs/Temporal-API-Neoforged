package com.temporal.api.core.event.data.map;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiDataMapProvider extends DataMapProvider {
    public static final List<FurnaceFuelDto> FURNACE_FUELS = new ArrayList<>();
    public static final List<CompostableDto> COMPOSTABLES = new ArrayList<>();
    public static final List<OxidizableDto> OXIDIZABLES = new ArrayList<>();
    public static final List<WaxableDto> WAXABLES = new ArrayList<>();
    public static final List<RaidHeroGiftDto> RAID_HERO_GIFTS = new ArrayList<>();

    public ApiDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(@NotNull HolderLookup.Provider provider) {
        Builder<FurnaceFuel, Item> fuelItemBuilder = this.builder(NeoForgeDataMaps.FURNACE_FUELS);
        FURNACE_FUELS.forEach(fuel ->
                fuelItemBuilder.add(fuel.item(), new FurnaceFuel(fuel.burnTime()), fuel.replace()));
        Builder<Compostable, Item> compostableItemBuilder = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        COMPOSTABLES.forEach(compostable ->
                compostableItemBuilder.add(compostable.item(), new Compostable(compostable.chance()), compostable.replace()));
        Builder<Oxidizable, Block> oxidizableBlockBuilder = this.builder(NeoForgeDataMaps.OXIDIZABLES);
        OXIDIZABLES.forEach(oxidizable ->
                oxidizableBlockBuilder.add(oxidizable.block(), new Oxidizable(IOHelper.getBlockById(oxidizable.nextStageBlockId())), oxidizable.replace()));
        Builder<Waxable, Block> waxableBlockBuilder = this.builder(NeoForgeDataMaps.WAXABLES);
        WAXABLES.forEach(waxable ->
                waxableBlockBuilder.add(waxable.block(), new Waxable(IOHelper.getBlockById(waxable.waxedBlock())), waxable.replace()));
        Builder<RaidHeroGift, VillagerProfession> raidHeroGiftVillagerProfessionBuilder = this.builder(NeoForgeDataMaps.RAID_HERO_GIFTS);
        RAID_HERO_GIFTS.forEach(raidHeroGift ->
                raidHeroGiftVillagerProfessionBuilder.add(raidHeroGift.villagerProfession(), new RaidHeroGift(ResourceKey.create(Registries.LOOT_TABLE, IOHelper.createNamespacedResourceLocation(raidHeroGift.lootTablePath()))), raidHeroGift.replace()));
    }
}
