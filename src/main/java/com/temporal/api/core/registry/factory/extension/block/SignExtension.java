package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

//todo
public interface SignExtension {
    default DeferredBlock<Block> createStandingSign(String name, float strength, WoodType woodType) {
        return createStandingSign(name, BlockPropertiesFactory.empty(), new Item.Properties(), strength, woodType);
    }

    default DeferredBlock<Block> createStandingSign(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, float strength, WoodType woodType) {
        BlockFactory factory = InjectionPool.getFromInstance(BlockFactory.class);
        return factory.create(name, properties.mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new StandingSignBlock(woodType, props), itemProperties);
    }

    default DeferredBlock<Block> createHangingSign(String name, MapColor color, float strength, WoodType woodType) {
        return createHangingSign(name, BlockPropertiesFactory.empty(), new Item.Properties(), color, strength, woodType);
    }

    default DeferredBlock<Block> createHangingSign(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, MapColor color, float strength, WoodType woodType) {
        BlockFactory factory = InjectionPool.getFromInstance(BlockFactory.class);
        return factory.create(name, properties.mapColor(color)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new CeilingHangingSignBlock(woodType, props), itemProperties);
    }

    default DeferredBlock<Block> createWallSign(String name, float strength, WoodType woodType) {
        return createWallSign(name, BlockPropertiesFactory.empty(), new Item.Properties(), strength, woodType);
    }

    default DeferredBlock<Block> createWallSign(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, float strength, WoodType woodType) {
        BlockFactory factory = InjectionPool.getFromInstance(BlockFactory.class);
        return factory.create(name, properties.mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new WallSignBlock(woodType, props), itemProperties);
    }
}
