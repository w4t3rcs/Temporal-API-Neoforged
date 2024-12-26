package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SignExtension {
    default DeferredBlock<?> createStandingSign(String name, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.createTyped(name, () -> new StandingSignBlock(woodType, BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava()));
    }

    default DeferredBlock<?> createHangingSign(String name, MapColor color, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.createTyped(name, () -> new CeilingHangingSignBlock(woodType, BlockBehaviour.Properties.of()
                .mapColor(color)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava()));
    }

    default DeferredBlock<?> createWallSign(String name, float strength, WoodType woodType) {
        BlockFactory factory = InjectionContext.getFromInstance(BlockFactory.class);
        return factory.createTyped(name, () -> new WallSignBlock(woodType, BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava()));
    }
}
