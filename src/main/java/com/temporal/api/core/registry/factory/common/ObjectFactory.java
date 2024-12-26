package com.temporal.api.core.registry.factory.common;

import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public interface ObjectFactory<T> {
    RegistryObject<T> create(String name, Supplier<T> tSupplier);

    void register();
}
