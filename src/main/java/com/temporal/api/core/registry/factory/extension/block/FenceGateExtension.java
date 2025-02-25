package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Optional;

public interface FenceGateExtension {
    default DeferredBlock<Block> createFenceGate(String name, BlockBehaviour.Properties properties, WoodType woodType, SoundEvent openSound, SoundEvent closeSound) {
        return createFenceGate(name, properties, new Item.Properties(), woodType, openSound, closeSound);
    }

    default DeferredBlock<Block> createFenceGate(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, WoodType woodType, SoundEvent openSound, SoundEvent closeSound) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties, (props) -> new FenceGateBlock(Optional.of(woodType), props, Optional.of(openSound), Optional.of(closeSound)), itemProperties);
    }
}
