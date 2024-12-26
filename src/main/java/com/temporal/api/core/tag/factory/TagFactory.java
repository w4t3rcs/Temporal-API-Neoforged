package com.temporal.api.core.tag.factory;

import net.minecraft.tags.TagKey;

public interface TagFactory<T> {
    TagKey<T> createTag(String name);
}
