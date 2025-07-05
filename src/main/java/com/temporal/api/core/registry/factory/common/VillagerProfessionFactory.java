package com.temporal.api.core.registry.factory.common;

import com.google.common.collect.ImmutableSet;
import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VillagerProfessionFactory implements ObjectFactory<VillagerProfession> {
    private final DeferredRegister<VillagerProfession> villagerProfessions;

    public VillagerProfessionFactory() {
        this(InjectionPool.getFromInstance("$VillagerProfessions"));
    }

    public VillagerProfessionFactory(DeferredRegister<VillagerProfession> villagerProfessions) {
        this.villagerProfessions = villagerProfessions;
    }

    public Holder<VillagerProfession> create(String name, String professionName, PoiType heldJobSite, SoundEvent workSound) {
        return create(name, () -> new VillagerProfession(professionName, holder -> holder.value() == heldJobSite, holder -> holder.value() == heldJobSite, ImmutableSet.of(), ImmutableSet.of(), workSound));
    }

    public Holder<VillagerProfession> create(String name, String professionName, PoiType heldJobSite, PoiType acquirableJobSite, SoundEvent workSound) {
        return create(name, () -> new VillagerProfession(professionName, holder -> holder.value() == heldJobSite, holder -> holder.value() == acquirableJobSite, ImmutableSet.of(), ImmutableSet.of(), workSound));
    }

    public Holder<VillagerProfession> create(String name, String professionName, PoiType heldJobSite, PoiType acquirableJobSite, ImmutableSet<Item> requestedItems, ImmutableSet<Block> secondaryPoi, SoundEvent workSound) {
        return create(name, () -> new VillagerProfession(professionName, holder -> holder.value() == heldJobSite, holder -> holder.value() == acquirableJobSite, requestedItems, secondaryPoi, workSound));
    }

    @Override
    public Holder<VillagerProfession> create(String name, Supplier<VillagerProfession> villagerProfessionSupplier) {
        return villagerProfessions.register(name, villagerProfessionSupplier);
    }

    @Override
    public DeferredRegister<VillagerProfession> getRegistry() {
        return villagerProfessions;
    }
}
