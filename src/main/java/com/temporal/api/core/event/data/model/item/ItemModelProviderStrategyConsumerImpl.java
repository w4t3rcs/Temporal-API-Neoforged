package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer.*;

public class ItemModelProviderStrategyConsumerImpl implements ItemModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull ItemModelGenerators itemModels) {
        BASIC_ITEMS.forEach(registerItemModel(itemModels, BasicItemModelProviderStrategy::new));
        HANDHELD_ITEMS.forEach(registerItemModel(itemModels, HandheldItemModelProviderStrategy::new));
        BOW_ITEMS.forEach(registerItemModel(itemModels, BowModelProviderStrategy::new));
        CROSSBOW_ITEMS.forEach(registerItemModel(itemModels, CrossbowModelProviderStrategy::new));
        SHIELD_ITEMS.forEach(registerItemModel(itemModels, ShieldModelProviderStrategy::new));
        TRIMMED_ARMOR_ITEMS.forEach(registerItemModel(itemModels, TrimmedItemModelProviderStrategy::new));
        POTION_ITEMS.forEach(registerItemModel(itemModels, PotionItemModelProviderStrategy::new));
        CUSTOM_MODELS.forEach((key, value) -> value.registerItemModel(key, itemModels));
    }

    @Override
    public Consumer<DeferredItem<?>> registerItemModel(@NotNull ItemModelGenerators itemModels, @NotNull Supplier<ItemModelProviderStrategy> itemModelProviderStrategy) {
        return (item) -> itemModelProviderStrategy.get().registerItemModel(item, itemModels);
    }
}
