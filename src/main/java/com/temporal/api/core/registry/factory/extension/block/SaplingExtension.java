package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import com.temporal.api.core.registry.factory.other.TreeGrowerFactory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SaplingExtension {
    default DeferredBlock<Block> createSapling(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return createSapling(name, BlockPropertiesFactory.sapling(), tree);
    }

    default DeferredBlock<Block> createSapling(String name, BlockBehaviour.Properties properties, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return createSapling(name, properties, TreeGrowerFactory.create(name, tree));
    }

    default DeferredBlock<Block> createSapling(String name, BlockBehaviour.Properties properties, TreeGrower treeGrower) {
        return createSapling(name, properties, new Item.Properties(), treeGrower);
    }

    default DeferredBlock<Block> createSapling(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, TreeGrower treeGrower) {
        final BlockFactory blockFactory = InjectionPool.getFromInstance(BlockFactory.class);
        return blockFactory.create(name, properties.noOcclusion().noCollission(), props -> new SaplingBlock(treeGrower, props), itemProperties);
    }
}
