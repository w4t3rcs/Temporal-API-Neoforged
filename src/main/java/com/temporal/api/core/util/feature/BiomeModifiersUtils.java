package com.temporal.api.core.util.feature;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class BiomeModifiersUtils {
    private BiomeModifiersUtils() {
    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, IOHelper.createResourceLocation(name));
    }
}
