package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.collection.TemporalMap;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;

public final class BlockModelDescriptionContainer {
    public static final Map<DeferredBlock<?>, Object[]> CUBED_BLOCKS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> CUTOUT_CUBED_BLOCKS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> CROSS_BLOCKS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> FLOWER_BLOCKS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> LOGS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> BUTTONS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> DOORS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> FENCES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> FENCE_GATES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> PRESSURE_PLATES = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SLABS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> STAIRS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> TRAPDOORS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> WALLS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> SIGNS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, Object[]> HANGING_SIGNS = new TemporalMap<>();
    public static final Map<DeferredBlock<?>, BlockModelProviderStrategy> CUSTOM_MODELS = new TemporalMap<>();

    private BlockModelDescriptionContainer() {
    }
}
