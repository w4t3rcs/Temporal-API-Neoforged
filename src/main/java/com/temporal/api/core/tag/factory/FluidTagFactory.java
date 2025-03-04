package com.temporal.api.core.tag.factory;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class FluidTagFactory implements TagFactory<Fluid> {
    @Override
    public TagKey<Fluid> createTag(String name) {
        return FluidTags.create(ResourceUtils.createResourceLocation(name));
    }
}
