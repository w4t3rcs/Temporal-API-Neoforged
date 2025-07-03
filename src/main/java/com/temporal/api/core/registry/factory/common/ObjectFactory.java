package com.temporal.api.core.registry.factory.common;

import net.minecraft.core.Holder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ObjectFactory<T> {
    Holder<T> create(String name, Supplier<T> tSupplier);

    default void register(IEventBus eventBus) {
        getRegistry().register(eventBus);
    }

    DeferredRegister<T> getRegistry();
}
