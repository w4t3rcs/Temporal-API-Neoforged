package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface CropExtension {
    default DeferredBlock<Block> createCrop(String name) {
        return createCrop(name, BlockPropertiesFactory.crop());
    }

    default DeferredBlock<Block> createCrop(String name, BlockBehaviour.Properties properties) {
        return createCrop(name, properties, new Item.Properties());
    }

    default DeferredBlock<Block> createCrop(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        final BlockFactory blockFactory = InjectionContext.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion().noCollission(), CropBlock::new, itemProperties);
    }
}
