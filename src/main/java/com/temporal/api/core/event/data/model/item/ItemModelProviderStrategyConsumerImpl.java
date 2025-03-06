package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
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
        CUSTOM_MODELS.forEach((key, value) -> {
            ((ItemModelProviderStrategy<Item>) value).registerItemModel((DeferredItem<Item>) key, itemModels);
        });
    }

    @Override
    public <T extends Item> Consumer<? super DeferredItem<T>> registerItemModel(@NotNull ItemModelGenerators itemModels, @NotNull Supplier<ItemModelProviderStrategy<T>> itemModelProviderStrategy) {
        return (item) -> itemModelProviderStrategy.get().registerItemModel(item, itemModels);
    }
}
