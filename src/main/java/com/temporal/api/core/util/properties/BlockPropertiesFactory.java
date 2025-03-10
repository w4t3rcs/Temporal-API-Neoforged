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

    public static Properties wool() {
        return copy(Blocks.WHITE_WOOL);
    }

    public static Properties dirt() {
        return copy(Blocks.DIRT);
    }

    public static Properties stone() {
        return copy(Blocks.STONE);
    }

    public static Properties deepslate() {
        return copy(Blocks.DEEPSLATE);
    }

    public static Properties shortGrass() {
        return copy(Blocks.SHORT_GRASS);
    }

    public static Properties flower() {
        return copy(Blocks.ALLIUM);
    }

    public static Properties mushroom() {
        return copy(Blocks.BROWN_MUSHROOM);
    }

    public static Properties flowerPot() {
        return copy(Blocks.FLOWER_POT);
    }

    public static Properties sapling() {
        return copy(Blocks.OAK_SAPLING);
    }

    public static Properties leaves() {
        return copy(Blocks.OAK_LEAVES);
    }

    public static Properties wood() {
        return copy(Blocks.OAK_WOOD);
    }

    public static Properties planks() {
        return copy(Blocks.OAK_PLANKS);
    }

    public static Properties button() {
        return copy(Blocks.OAK_BUTTON);
    }

    public static Properties pressurePlate() {
        return copy(Blocks.OAK_PRESSURE_PLATE);
    }

    public static Properties fence() {
        return copy(Blocks.OAK_FENCE);
    }

    public static Properties fenceGate() {
        return copy(Blocks.OAK_FENCE_GATE);
    }

    public static Properties door() {
        return copy(Blocks.OAK_DOOR);
    }

    public static Properties trapdoor() {
        return copy(Blocks.OAK_TRAPDOOR);
    }

    public static Properties copy(BlockBehaviour block) {
        return Properties.ofFullCopy(block);
    }

    public static Properties init(Properties properties, ResourceLocation resourceLocation) {
        return properties.setId(ResourceKey.create(Registries.BLOCK, resourceLocation));
    }
}
