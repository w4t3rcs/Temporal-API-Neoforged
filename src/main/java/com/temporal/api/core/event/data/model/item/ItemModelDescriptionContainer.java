package com.temporal.api.core.event.data.model.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public final class ItemModelDescriptionContainer {
    public static final List<DeferredItem<Item>> BASIC_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> HANDHELD_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> BOW_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> CROSSBOW_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> SHIELD_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> TRIMMED_ARMOR_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<Item>> POTION_ITEMS = new ArrayList<>();

    private ItemModelDescriptionContainer() {
    }
}
