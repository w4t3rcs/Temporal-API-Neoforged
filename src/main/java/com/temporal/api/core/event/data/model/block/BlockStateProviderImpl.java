package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;

public class BlockStateProviderImpl extends ApiBlockStateProvider {
    public static final List<DeferredBlock<Block>> BLOCKS = new ArrayList<>();
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

    public BlockStateProviderImpl(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BLOCKS.forEach(this::registerCubedBlock);
        BUTTONS.forEach(this::registerButtonBlock);
        DOORS.forEach(this::registerDoorBlock);
        FENCES.forEach(this::registerFenceBlock);
        FENCE_GATES.forEach(this::registerFenceGateBlock);
        PRESSURE_PLATES.forEach(this::registerPressurePlateBlock);
        SLABS.forEach(this::registerSlabBlock);
        STAIRS.forEach(this::registerStairsBlock);
        TRAPDOORS.forEach(this::registerTrapdoorBlock);
        WALLS.forEach(this::registerWallBlock);
        LOGS.forEach(this::registerLogBlock);
        ROTATED_PILLARS.forEach(this::registerRotatedPillarBlock);
    }
}
