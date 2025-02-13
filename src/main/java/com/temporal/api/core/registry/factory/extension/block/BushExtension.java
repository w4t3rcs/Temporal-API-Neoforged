package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface BushExtension {
    default DeferredBlock<Block> createBush(String name, BlockBehaviour.Properties properties) {
        return createBush(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createBush(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion().noCollission(), SweetBerryBushBlock::new, itemProperties);
    }
}
