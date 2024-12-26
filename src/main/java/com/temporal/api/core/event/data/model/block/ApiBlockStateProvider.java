package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.LogBlock;
import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.apache.commons.lang3.StringUtils;

public abstract class ApiBlockStateProvider extends BlockStateProvider {
    public ApiBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IOLayer.NEO_MOD.getModId(), exFileHelper);
    }

    protected void registerCubedBlock(DeferredBlock<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        this.simpleBlockWithItem(block, this.cubeAll(block));
    }

    protected void registerButtonBlock(DeferredBlock<ButtonBlock> blockRegistryObject) {
        ButtonBlock block = blockRegistryObject.get();
        this.buttonBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerDoorBlock(DeferredBlock<DoorBlock> blockRegistryObject) {
        DoorBlock block = blockRegistryObject.get();
        String path = "block/" + blockRegistryObject.getId().getPath();
        this.doorBlock(block, IOHelper.createResourceLocation(path + "_top"), IOHelper.createResourceLocation(path + "_bottom"));
    }

    protected void registerFenceBlock(DeferredBlock<FenceBlock> blockRegistryObject) {
        FenceBlock block = blockRegistryObject.get();
        this.fenceBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerFenceGateBlock(DeferredBlock<FenceGateBlock> blockRegistryObject) {
        FenceGateBlock block = blockRegistryObject.get();
        this.fenceGateBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerPressurePlateBlock(DeferredBlock<PressurePlateBlock> blockRegistryObject) {
        PressurePlateBlock block = blockRegistryObject.get();
        this.pressurePlateBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerSignBlock(DeferredBlock<StandingSignBlock> standingSignBlockRegistryObject, DeferredBlock<WallSignBlock> wallSignBlockRegistryObject) {
        StandingSignBlock standingSignBlock = standingSignBlockRegistryObject.get();
        WallSignBlock wallSignBlock = wallSignBlockRegistryObject.get();
        this.signBlock(standingSignBlock, wallSignBlock, getLocation(wallSignBlockRegistryObject));
    }

    protected void registerSlabBlock(DeferredBlock<SlabBlock> blockRegistryObject) {
        SlabBlock block = blockRegistryObject.get();
        String path = blockRegistryObject.getId().getPath();
        ResourceLocation texture = IOHelper.createResourceLocation("block/" + StringUtils.substringBefore(path, "_slab"));
        this.slabBlock(block, texture, texture);
        this.simpleBlockItem(block, models().slab(path, texture, texture, texture));
    }

    protected void registerSlabBlock(DeferredBlock<SlabBlock> slabBlockRegistryObject, DeferredBlock<Block> blockRegistryObject) {
        SlabBlock block = slabBlockRegistryObject.get();
        this.slabBlock(block, getLocation(blockRegistryObject), getLocation(blockRegistryObject));
    }

    protected void registerStairsBlock(DeferredBlock<StairBlock> blockRegistryObject) {
        StairBlock block = blockRegistryObject.get();
        this.stairsBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerTrapdoorBlock(DeferredBlock<TrapDoorBlock> blockRegistryObject) {
        this.registerTrapdoorBlock(blockRegistryObject, true);
    }

    protected void registerTrapdoorBlock(DeferredBlock<TrapDoorBlock> blockRegistryObject, boolean orientable) {
        TrapDoorBlock block = blockRegistryObject.get();
        this.trapdoorBlock(block, getLocation(blockRegistryObject), orientable);
    }

    protected void registerWallBlock(DeferredBlock<WallBlock> blockRegistryObject) {
        WallBlock block = blockRegistryObject.get();
        this.wallBlock(block, getLocation(blockRegistryObject));
    }

    protected void registerLogBlock(DeferredBlock<LogBlock> blockRegistryObject) {
        LogBlock block = blockRegistryObject.get();
        this.logBlock(block);
    }

    protected void registerRotatedPillarBlock(DeferredBlock<RotatedPillarBlock> blockRegistryObject) {
        RotatedPillarBlock block = blockRegistryObject.get();
        this.axisBlock(block);
    }

    protected ResourceLocation getLocation(DeferredBlock<?> registryObject) {
        return IOHelper.createResourceLocation("block/" + registryObject.getId().getPath());
    }
}
