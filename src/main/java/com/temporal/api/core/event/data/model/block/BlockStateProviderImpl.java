package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class BlockStateProvider extends ApiBlockStateProvider {
    public static final List<RegistryObject<Block>> BLOCKS = new ArrayList<>();
    public static final List<RegistryObject<ButtonBlock>> BUTTONS = new ArrayList<>();
    public static final List<RegistryObject<DoorBlock>> DOORS = new ArrayList<>();
    public static final List<RegistryObject<FenceBlock>> FENCES = new ArrayList<>();
    public static final List<RegistryObject<FenceGateBlock>> FENCE_GATES = new ArrayList<>();
    public static final List<RegistryObject<PressurePlateBlock>> PRESSURE_PLATES = new ArrayList<>();
    public static final List<RegistryObject<SlabBlock>> SLABS = new ArrayList<>();
    public static final List<RegistryObject<StairBlock>> STAIRS = new ArrayList<>();
    public static final List<RegistryObject<TrapDoorBlock>> TRAPDOORS = new ArrayList<>();
    public static final List<RegistryObject<WallBlock>> WALLS = new ArrayList<>();
    public static final List<RegistryObject<LogBlock>> LOGS = new ArrayList<>();
    public static final List<RegistryObject<RotatedPillarBlock>> ROTATED_PILLARS = new ArrayList<>();

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
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
