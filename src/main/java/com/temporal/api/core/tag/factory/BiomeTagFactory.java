package com.temporal.api.core.tag.factory;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeTagFactory implements TagFactory<Biome> {
    @Override
    public TagKey<Biome> createTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceUtils.createResourceLocation(name));
    }
}
