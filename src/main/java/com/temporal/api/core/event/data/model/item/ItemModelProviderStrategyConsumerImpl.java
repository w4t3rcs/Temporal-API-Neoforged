package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.temporal.api.core.event.data.model.item.ItemModelDescriptionContainer.*;

public class ItemModelProviderStrategyConsumerImpl implements ItemModelProviderStrategyConsumer {
    @Override
    public void registerModels(@NotNull ItemModelGenerators itemModels) {
        var basicItemModelProviderStrategy = new BasicItemModelProviderStrategy(itemModels);
        BASIC_ITEMS.forEach(registerItemModel(basicItemModelProviderStrategy));
        var handheldItemModelProviderStrategy = new HandheldItemModelProviderStrategy(itemModels);
        HANDHELD_ITEMS.forEach(registerItemModel(handheldItemModelProviderStrategy));
        var bowModelProviderStrategy = new BowModelProviderStrategy(itemModels);
        BOW_ITEMS.forEach(registerItemModel(bowModelProviderStrategy));
        var crossbowModelProviderStrategy = new CrossbowModelProviderStrategy(itemModels);
        CROSSBOW_ITEMS.forEach(registerItemModel(crossbowModelProviderStrategy));
        var shieldModelProviderStrategy = new ShieldModelProviderStrategy(itemModels);
        SHIELD_ITEMS.forEach(registerItemModel(shieldModelProviderStrategy));
        var trimmedItemModelProviderStrategy = new TrimmedItemModelProviderStrategy(itemModels);
        TRIMMED_ARMOR_ITEMS.forEach(registerItemModel(trimmedItemModelProviderStrategy));
        var potionItemModelProviderStrategy = new PotionItemModelProviderStrategy(itemModels);
        POTION_ITEMS.forEach(registerItemModel(potionItemModelProviderStrategy));
    }

    @Override
    public <T extends Item> Consumer<? super DeferredItem<T>> registerItemModel(@NotNull ItemModelProviderStrategy<T> itemModelProviderStrategy) {
        return itemModelProviderStrategy::registerItemModel;
    }
}
