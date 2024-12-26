package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SignExtension {
    default DeferredBlock<Block> createStandingSign(String name, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.create(name, BlockPropertiesFactory.empty()
                .mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new StandingSignBlock(woodType, props));
    }

    default DeferredBlock<Block> createHangingSign(String name, MapColor color, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.create(name, BlockPropertiesFactory.empty()
                .mapColor(color)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new CeilingHangingSignBlock(woodType, props));
    }

    default DeferredBlock<Block> createWallSign(String name, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.create(name, BlockPropertiesFactory.empty()
                .mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new WallSignBlock(woodType, props));
    }
}
