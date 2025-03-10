package com.temporal.api.core.event.data.model.block;

import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BlockModelDescriptionContainer {
    public static final List<DeferredBlock<?>> CUBED_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<?>> CUTOUT_CUBED_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<?>> CROSS_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<?>> FLOWER_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<?>> BUTTONS = new ArrayList<>();
    public static final List<DeferredBlock<?>> DOORS = new ArrayList<>();
    public static final List<DeferredBlock<?>> FENCES = new ArrayList<>();
    public static final List<DeferredBlock<?>> FENCE_GATES = new ArrayList<>();
    public static final List<DeferredBlock<?>> PRESSURE_PLATES = new ArrayList<>();
    public static final List<DeferredBlock<?>> SLABS = new ArrayList<>();
    public static final List<DeferredBlock<?>> STAIRS = new ArrayList<>();
    public static final List<DeferredBlock<?>> TRAPDOORS = new ArrayList<>();
    public static final List<DeferredBlock<?>> WALLS = new ArrayList<>();
    public static final List<DeferredBlock<?>> LOGS = new ArrayList<>();
    public static final List<DeferredBlock<?>> WOOD = new ArrayList<>();
    public static final List<DeferredBlock<?>> ROTATED_PILLARS = new ArrayList<>();
    public static final List<DeferredBlock<?>> CROP_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<?>> VINE_BLOCKS = new ArrayList<>();
    public static final Map<DeferredBlock<?>, BlockModelProviderStrategy> CUSTOM_MODELS = new HashMap<>();

    private BlockModelDescriptionContainer() {
    }
}
