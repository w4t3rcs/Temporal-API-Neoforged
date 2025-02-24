package com.temporal.api.core.registry.factory.other;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class TreeGrowerFactory {
    private TreeGrowerFactory() {
    }

    public static TreeGrower create(String name) {
        return new TreeGrower(IOLayer.NEO_MOD.getModId() + ":" + name,
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }
}
