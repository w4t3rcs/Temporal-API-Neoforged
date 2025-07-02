package com.temporal.api.core.event.data.model.item;

import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer.*;

public class ItemModelProviderStrategyConsumerImpl implements ItemModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull ApiItemModelProvider provider) {
        BASIC_ITEMS.forEach(registerItemModel(provider, BasicItemModelProviderStrategy::new));
        HANDHELD_ITEMS.forEach(registerItemModel(provider, HandheldItemModelProviderStrategy::new));
        TRIMMED_ARMOR_ITEMS.forEach(registerItemModel(provider, TrimmedItemModelProviderStrategy::new));
        POTION_ITEMS.forEach(registerItemModel(provider, PotionItemModelProviderStrategy::new));
        CUSTOM_MODELS.forEach((key, value) -> value.registerItemModel(key, provider));
    }

    @Override
    public Consumer<DeferredItem<?>> registerItemModel(@NotNull ApiItemModelProvider provider, @NotNull Supplier<ItemModelProviderStrategy> itemModelProviderStrategy) {
        return (item) -> itemModelProviderStrategy.get().registerItemModel(item, provider);
    }
}
