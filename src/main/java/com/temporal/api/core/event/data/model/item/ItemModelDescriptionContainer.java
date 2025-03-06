package com.temporal.api.core.event.data.model.item;

import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ItemModelDescriptionContainer {
    public static final List<DeferredItem<?>> BASIC_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> HANDHELD_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> BOW_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> CROSSBOW_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> SHIELD_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> TRIMMED_ARMOR_ITEMS = new ArrayList<>();
    public static final List<DeferredItem<?>> POTION_ITEMS = new ArrayList<>();
    public static final Map<DeferredItem<?>, ItemModelProviderStrategy> CUSTOM_MODELS = new HashMap<>();

    private ItemModelDescriptionContainer() {
    }
}
