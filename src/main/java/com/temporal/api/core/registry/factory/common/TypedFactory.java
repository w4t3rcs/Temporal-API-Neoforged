package com.temporal.api.core.registry.factory.common;

import net.minecraft.core.Holder;

import java.util.function.Supplier;

public interface TypedFactory<T> extends ObjectFactory<T> {
    Holder<? extends T> createTyped(String name, Supplier<? extends T> tSupplier);
}
