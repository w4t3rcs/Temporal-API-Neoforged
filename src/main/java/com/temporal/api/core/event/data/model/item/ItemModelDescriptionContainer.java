package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.collection.TemporalMap;
import com.temporal.api.core.collection.TemporalQueue;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.Queue;

public final class ItemModelDescriptionContainer {
    public static final Queue<DeferredItem<?>> BASIC_ITEMS = new TemporalQueue<>();
    public static final Queue<DeferredItem<?>> HANDHELD_ITEMS = new TemporalQueue<>();
    public static final Queue<DeferredItem<?>> BOW_ITEMS = new TemporalQueue<>();
    public static final Queue<DeferredItem<?>> CROSSBOW_ITEMS = new TemporalQueue<>();
    public static final Queue<DeferredItem<?>> TRIMMED_ARMOR_ITEMS = new TemporalQueue<>();
    public static final Queue<DeferredItem<?>> POTION_ITEMS = new TemporalQueue<>();
    public static final Map<DeferredItem<?>, ItemModelProviderStrategy> CUSTOM_MODELS = new TemporalMap<>();

    private ItemModelDescriptionContainer() {
    }
}
