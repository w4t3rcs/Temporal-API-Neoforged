package com.temporal.api.core.event.data.model.item;

import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ItemModelProviderStrategyConsumer {
    void registerModels(@NotNull ApiItemModelProvider provider);

    Consumer<DeferredItem<?>> registerItemModel(@NotNull ApiItemModelProvider provider, @NotNull Supplier<ItemModelProviderStrategy> itemModelProviderStrategy);
}
