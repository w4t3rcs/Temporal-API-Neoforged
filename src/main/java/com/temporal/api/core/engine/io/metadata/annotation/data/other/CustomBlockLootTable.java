package com.temporal.api.core.engine.io.metadata.annotation.data.other;

import com.temporal.api.core.event.data.loot.LootProviderStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomBlockLootTable {
    Class<LootProviderStrategy> value();
}