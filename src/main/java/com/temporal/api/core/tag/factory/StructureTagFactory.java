package com.temporal.api.core.tag.factory;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class StructureTagFactory implements TagFactory<Structure> {
    @Override
    public TagKey<Structure> createTag(String name) {
        return TagKey.create(Registries.STRUCTURE, ResourceUtils.createResourceLocation(name));
    }
}
