package com.temporal.api.core.util.properties;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class BlockPropertiesFactory {
    private BlockPropertiesFactory() {
    }

    public static Properties empty() {
        return Properties.of();
    }

    public static Properties woolLike() {
        return copy(Blocks.WHITE_WOOL);
    }

    public static Properties woodLike() {
        return copy(Blocks.OAK_WOOD);
    }

    public static Properties dirtLike() {
        return copy(Blocks.DIRT);
    }

    public static Properties stoneLike() {
        return copy(Blocks.STONE);
    }

    public static Properties deepslateLike() {
        return copy(Blocks.DEEPSLATE);
    }

    public static Properties shortGrassLike() {
        return copy(Blocks.SHORT_GRASS);
    }

    public static Properties flowerLike() {
        return copy(Blocks.ALLIUM);
    }

    public static Properties leavesLike() {
        return copy(Blocks.OAK_LEAVES);
    }

    public static Properties copy(BlockBehaviour block) {
        return Properties.ofFullCopy(block);
    }

    public static Properties init(Properties properties, ResourceLocation resourceLocation) {
        return properties.setId(ResourceKey.create(Registries.BLOCK, resourceLocation));
    }
}
