package com.temporal.api.core.registry.factory.common;

import com.google.common.collect.ImmutableSet;
import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class PoiTypeFactory implements ObjectFactory<PoiType> {
    private final DeferredRegister<PoiType> poiTypes;

    public PoiTypeFactory() {
        this(InjectionPool.getFromInstance("$PoiTypes"));
    }

    public PoiTypeFactory(DeferredRegister<PoiType> poiTypes) {
        this.poiTypes = poiTypes;
    }

    public Holder<PoiType> create(String name, Block block, int maxTickets, int validRange) {
        return create(name, ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()), maxTickets, validRange);
    }

    public Holder<PoiType> create(String name, Set<BlockState> matchingStates, int maxTickets, int validRange) {
        return create(name, () -> new PoiType(matchingStates, maxTickets, validRange));
    }

    @Override
    public Holder<PoiType> create(String name, Supplier<PoiType> poiSupplier) {
        return poiTypes.register(name, poiSupplier);
    }

    @Override
    public DeferredRegister<PoiType> getRegistry() {
        return poiTypes;
    }
}
