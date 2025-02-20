package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;

public final class BlockModelDescriptionContainer {
    public static final List<DeferredBlock<Block>> CUBED_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<ButtonBlock>> BUTTONS = new ArrayList<>();
    public static final List<DeferredBlock<DoorBlock>> DOORS = new ArrayList<>();
    public static final List<DeferredBlock<FenceBlock>> FENCES = new ArrayList<>();
    public static final List<DeferredBlock<FenceGateBlock>> FENCE_GATES = new ArrayList<>();
    public static final List<DeferredBlock<PressurePlateBlock>> PRESSURE_PLATES = new ArrayList<>();
    public static final List<DeferredBlock<SlabBlock>> SLABS = new ArrayList<>();
    public static final List<DeferredBlock<StairBlock>> STAIRS = new ArrayList<>();
    public static final List<DeferredBlock<TrapDoorBlock>> TRAPDOORS = new ArrayList<>();
    public static final List<DeferredBlock<WallBlock>> WALLS = new ArrayList<>();
    public static final List<DeferredBlock<LogBlock>> LOGS = new ArrayList<>();
    public static final List<DeferredBlock<RotatedPillarBlock>> ROTATED_PILLARS = new ArrayList<>();


    private BlockModelDescriptionContainer() {
    }
}
