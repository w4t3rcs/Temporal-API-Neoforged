package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.collection.TemporalHashMap;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.Queue;

public final class ItemModelDescriptionContainer {
    public static final Queue<DeferredItem<?>> BASIC_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> HANDHELD_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> BOW_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> CROSSBOW_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> SHIELD_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> TRIMMED_ARMOR_ITEMS = new TemporalArrayDeque<>();
    public static final Queue<DeferredItem<?>> POTION_ITEMS = new TemporalArrayDeque<>();
    public static final Map<DeferredItem<?>, ItemModelProviderStrategy> CUSTOM_MODELS = new TemporalHashMap<>();

    private ItemModelDescriptionContainer() {
    }
}
