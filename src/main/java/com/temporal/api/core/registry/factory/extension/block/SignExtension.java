package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SignExtension {
    default DeferredBlock<Block> createWallSign(String name, float strength, WoodType woodType) {
        return createWallSign(name, BlockPropertiesFactory.wood(), new Item.Properties(), strength, woodType);
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

    default DeferredBlock<Block> createStandingSign(String name, float strength, WoodType woodType) {
        return createStandingSign(name, BlockPropertiesFactory.wood(), new Item.Properties(), strength, woodType);
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
}
