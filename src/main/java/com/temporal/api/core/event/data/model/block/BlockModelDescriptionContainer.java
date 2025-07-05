package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.collection.TemporalMap;
import com.temporal.api.core.collection.TemporalQueue;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.Queue;

public final class BlockModelDescriptionContainer {
    public static final Queue<DeferredBlock<?>> CUBED_BLOCKS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> CUTOUT_CUBED_BLOCKS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> CROSS_BLOCKS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> FLOWER_BLOCKS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> LOGS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> BUTTONS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> DOORS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> FENCES = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> FENCE_GATES = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> PRESSURE_PLATES = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> SLABS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> STAIRS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> TRAPDOORS = new TemporalQueue<>();
    public static final Queue<DeferredBlock<?>> WALLS = new TemporalQueue<>();
    public static final Map<DeferredBlock<?>, BlockModelProviderStrategy> CUSTOM_MODELS = new TemporalMap<>();

    private BlockModelDescriptionContainer() {
    }
}
