package com.temporal.api.core.tag.factory;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class BiomeModifierTagFactory implements TagFactory<BiomeModifier> {
    @Override
    public TagKey<BiomeModifier> createTag(String name) {
        return TagKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceUtils.createResourceLocation(name));
    }
}
