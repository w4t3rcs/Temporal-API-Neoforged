package com.temporal.api.core.registry.factory.common;

import net.minecraft.core.Holder;

import java.util.function.Supplier;

public interface ObjectFactory<T> {
    Holder<T> create(String name, Supplier<T> tSupplier);

    void register();
}
