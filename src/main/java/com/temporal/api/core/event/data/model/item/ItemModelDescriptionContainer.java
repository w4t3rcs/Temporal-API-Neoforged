package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.collection.TemporalMap;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public final class ItemModelDescriptionContainer {
    public static final Map<DeferredItem<?>, Object[]> BASIC_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, Object[]> HANDHELD_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, Object[]> BOW_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, Object[]> CROSSBOW_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, Object[]> TRIMMED_ARMOR_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, Object[]> POTION_ITEMS = new TemporalMap<>();
    public static final Map<DeferredItem<?>, ItemModelProviderStrategy> CUSTOM_MODELS = new TemporalMap<>();

    private ItemModelDescriptionContainer() {
    }
}
