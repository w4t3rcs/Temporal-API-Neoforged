package com.temporal.api.core.event.data.preparer.tag.biome;

import com.temporal.api.core.event.data.preparer.DynamicPreparer;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;

import java.util.*;

public final class BiomeTagDynamicPreparer implements DynamicPreparer {
    public static final Set<Class<?>> TAG_CONTAINERS = new HashSet<>(List.of(BiomeTags.class, Tags.Biomes.class));
    public static final Map<String, TagKey<Biome>> BIOME_TAGS = new HashMap<>();

    @Override
    public void prepare() {
        TAG_CONTAINERS.stream()
                .flatMap(ResourceUtils::<Biome>getTagKeyStream)
                .forEach(tag -> {
                    String path = tag.location().getPath();
                    BIOME_TAGS.putIfAbsent(path, tag);
                });
    }
}
