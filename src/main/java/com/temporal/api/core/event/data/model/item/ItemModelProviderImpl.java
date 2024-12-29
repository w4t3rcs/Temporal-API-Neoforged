package com.temporal.api.core.event.data.model.item;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public class ItemModelProviderImpl extends ApiItemModelProvider {
    public static final List<DeferredItem<Item>> ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> HANDHELD_ITEMS = new ArrayList<>();

    public ItemModelProviderImpl(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ITEMS.stream()
                .map(DeferredHolder::get)
                .forEach(this::basicItem);
        HANDHELD_ITEMS.stream()
                .map(DeferredHolder::get)
                .forEach(this::handheldItem);
    }
}
