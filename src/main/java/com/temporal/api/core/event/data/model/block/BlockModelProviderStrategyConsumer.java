package com.temporal.api.core.event.data.model.block;

import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface BlockModelProviderStrategyConsumer {
    void registerModels(@NotNull ApiBlockModelProvider provider);

    Consumer<DeferredBlock<?>> registerBlockModel(@NotNull ApiBlockModelProvider provider, @NotNull Supplier<BlockModelProviderStrategy> blockModelProviderStrategy);
}
