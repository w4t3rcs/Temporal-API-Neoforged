package com.temporal.api.core.util.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockPropertiesFactory {
    public static Properties empty() {
        return Properties.of();
    }

    public static Properties woolLike() {
        return copy(Blocks.WHITE_WOOL);
    }

    public static Properties woodLike() {
        return copy(Blocks.OAK_WOOD);
    }

    public static Properties stoneLike() {
        return copy(Blocks.STONE);
    }

    public static Properties deepslateLike() {
        return copy(Blocks.DEEPSLATE);
    }

    public static Properties flowerLike() {
        return copy(Blocks.ALLIUM);
    }

    public static Properties copy(BlockBehaviour block) {
        return Properties.ofFullCopy(block);
    }
}
