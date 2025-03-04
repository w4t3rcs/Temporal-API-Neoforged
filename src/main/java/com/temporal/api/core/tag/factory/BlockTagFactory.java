package com.temporal.api.core.tag.factory;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTagFactory implements TagFactory<Block> {
    @Override
    public TagKey<Block> createTag(String name) {
        return BlockTags.create(ResourceUtils.createResourceLocation(name));
    }
}
