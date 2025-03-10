package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.util.properties.BlockPropertiesFactory;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface FlowerExtension {
    default DeferredBlock<Block> createFlower(String name, Holder<MobEffect> mobEffect, int duration) {
        return createFlower(name, BlockPropertiesFactory.flower(), mobEffect, duration);
    }

    default DeferredBlock<Block> createFlower(String name, BlockBehaviour.Properties properties, Holder<MobEffect> mobEffect, int duration) {
        return createFlower(name, properties, new Item.Properties(), mobEffect, duration);
    }

    default DeferredBlock<Block> createFlower(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, Holder<MobEffect> mobEffect, int duration) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion().noCollission(), props -> new FlowerBlock(mobEffect, duration, props), itemProperties);
    }
}
