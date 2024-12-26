package com.temporal.api.core.registry.factory.common;

import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public interface TypedFactory<T> extends ObjectFactory<T> {
    RegistryObject<? extends T> createTyped(String name, Supplier<? extends T> tSupplier);
}
