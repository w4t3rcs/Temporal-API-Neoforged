package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({"deprecated", "removal"})
public abstract class ApiBlockStateProvider extends BlockStateProvider {
    public ApiBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IOLayer.FORGE_MOD.getModId(), exFileHelper);
    }

    protected void registerCubedBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        this.simpleBlockWithItem(block, this.cubeAll(block));
    }

    protected void registerButtonBlock(RegistryObject<ButtonBlock> blockRegistryObject) {
        ButtonBlock block = blockRegistryObject.get();
        this.buttonBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerDoorBlock(RegistryObject<DoorBlock> blockRegistryObject) {
        DoorBlock block = blockRegistryObject.get();
        String path = "block/" + blockRegistryObject.getId().getPath();
        this.doorBlock(block, IOHelper.createResourceLocation(path + "_top"), IOHelper.createResourceLocation(path + "_bottom"));
    }

    protected void registerFenceBlock(RegistryObject<FenceBlock> blockRegistryObject) {
        FenceBlock block = blockRegistryObject.get();
        this.fenceBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerFenceGateBlock(RegistryObject<FenceGateBlock> blockRegistryObject) {
        FenceGateBlock block = blockRegistryObject.get();
        this.fenceGateBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerPressurePlateBlock(RegistryObject<PressurePlateBlock> blockRegistryObject) {
        PressurePlateBlock block = blockRegistryObject.get();
        this.pressurePlateBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerSignBlock(RegistryObject<StandingSignBlock> standingSignBlockRegistryObject, RegistryObject<WallSignBlock> wallSignBlockRegistryObject) {
        StandingSignBlock standingSignBlock = standingSignBlockRegistryObject.get();
        WallSignBlock wallSignBlock = wallSignBlockRegistryObject.get();
        this.signBlock(standingSignBlock, wallSignBlock, getLocation(wallSignBlockRegistryObject));
    }

    protected void registerSlabBlock(RegistryObject<SlabBlock> blockRegistryObject) {
        SlabBlock block = blockRegistryObject.get();
        String path = blockRegistryObject.getId().getPath();
        ResourceLocation texture = IOHelper.createResourceLocation("block/" + StringUtils.substringBefore(path, "_slab"));
        this.slabBlock(block, texture, texture);
        this.simpleBlockItem(block, models().slab(path, texture, texture, texture));
    }

    protected void registerSlabBlock(RegistryObject<SlabBlock> slabBlockRegistryObject, RegistryObject<Block> blockRegistryObject) {
        SlabBlock block = slabBlockRegistryObject.get();
        this.slabBlock(block, getLocation(blockRegistryObject), getLocation(blockRegistryObject));
    }

    protected void registerStairsBlock(RegistryObject<StairBlock> blockRegistryObject) {
        StairBlock block = blockRegistryObject.get();
        this.stairsBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerTrapdoorBlock(RegistryObject<TrapDoorBlock> blockRegistryObject) {
        this.registerTrapdoorBlock(blockRegistryObject, true);
    }

    protected void registerTrapdoorBlock(RegistryObject<TrapDoorBlock> blockRegistryObject, boolean orientable) {
        TrapDoorBlock block = blockRegistryObject.get();
        this.trapdoorBlock(block, getLocation(blockRegistryObject), orientable);
    }

    protected void registerWallBlock(RegistryObject<WallBlock> blockRegistryObject) {
        WallBlock block = blockRegistryObject.get();
        this.wallBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerLogBlock(RegistryObject<LogBlock> blockRegistryObject) {
        LogBlock block = blockRegistryObject.get();
        this.logBlock(block);
    }

    protected void registerRotatedPillarBlock(RegistryObject<RotatedPillarBlock> blockRegistryObject) {
        RotatedPillarBlock block = blockRegistryObject.get();
        this.axisBlock(block);
    }

    protected ResourceLocation getLocation(RegistryObject<?> registryObject) {
        return IOHelper.createResourceLocation("block/" + registryObject.getId().getPath());
    }
}
