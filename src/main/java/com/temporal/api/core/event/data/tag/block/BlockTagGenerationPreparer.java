package com.temporal.api.core.event.data.tag.block;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.event.data.tag.TagGenerationPreparer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.*;

public final class BlockTagGenerationPreparer implements TagGenerationPreparer {
    public static final Set<Class<?>> TAG_CONTAINERS = new HashSet<>(List.of(BlockTags.class, Tags.Blocks.class));
    public static final Map<String, TagKey<Block>> BLOCK_TAGS = new HashMap<>();

    @Override
    public void prepare() {
        TAG_CONTAINERS.stream()
                .flatMap(IOHelper::<Block>getTagKeyStream)
                .forEach(tag -> {
                    String path = tag.location().getPath();
                    BLOCK_TAGS.putIfAbsent(path, tag);
                });
    }
}
