package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer.BASIC_ITEMS;
import static com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer.HANDHELD_ITEMS;

public class ItemModelProviderStrategyConsumerImpl implements ItemModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull ItemModelGenerators itemModels) {
        BASIC_ITEMS.forEach(registerItemModel(new BasicItemModelProviderStrategy(itemModels)));
        HANDHELD_ITEMS.forEach(registerItemModel(new HandheldItemModelProviderStrategy(itemModels)));
    }

    @Override
    public <T extends Item> Consumer<? super DeferredItem<T>> registerItemModel(@NotNull ItemModelProviderStrategy<T> itemModelProviderStrategy) {
        return itemModelProviderStrategy::registerItemModel;
    }
}
