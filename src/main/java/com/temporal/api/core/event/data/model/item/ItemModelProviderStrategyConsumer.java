package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ItemModelProviderStrategyConsumer {
    void registerModels(@NotNull ItemModelGenerators itemModels);

    <T extends Item> Consumer<? super DeferredItem<T>> registerItemModel(@NotNull ItemModelGenerators itemModels, @NotNull Supplier<ItemModelProviderStrategy<T>> itemModelProviderStrategy);
}
