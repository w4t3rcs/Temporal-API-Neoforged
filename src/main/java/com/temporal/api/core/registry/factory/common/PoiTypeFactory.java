package com.temporal.api.core.registry.factory.common;

import com.google.common.collect.ImmutableSet;
import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class PoiTypeFactory implements ObjectFactory<PoiType> {
    public static final DeferredRegister<PoiType> POI_TYPES = IOHelper.createRegistry(Registries.POINT_OF_INTEREST_TYPE);

    public Holder<PoiType> create(String name, Block block, int maxTickets, int validRange) {
        return create(name, ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()), maxTickets, validRange);
    }

    public Holder<PoiType> create(String name, Set<BlockState> matchingStates, int maxTickets, int validRange) {
        return create(name, () -> new PoiType(matchingStates, maxTickets, validRange));
    }

    @Override
    public Holder<PoiType> create(String name, Supplier<PoiType> poiSupplier) {
        return POI_TYPES.register(name, poiSupplier);
    }

    @Override
    public void register() {
        POI_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
