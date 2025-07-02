package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.collection.TemporalHashMap;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.Queue;

public final class BlockModelDescriptionContainer {
    public static final Queue<DeferredBlock<?>> CUBED_BLOCKS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> CUTOUT_CUBED_BLOCKS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> CROSS_BLOCKS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> FLOWER_BLOCKS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> LOGS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> BUTTONS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> DOORS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> FENCES = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> FENCE_GATES = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> PRESSURE_PLATES = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> SLABS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> STAIRS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> TRAPDOORS = new TemporalArrayDeque<>();
    public static final Queue<DeferredBlock<?>> WALLS = new TemporalArrayDeque<>();
    public static final Map<DeferredBlock<?>, BlockModelProviderStrategy> CUSTOM_MODELS = new TemporalHashMap<>();

    private BlockModelDescriptionContainer() {
    }
}
