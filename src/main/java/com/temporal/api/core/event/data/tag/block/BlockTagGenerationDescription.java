package com.temporal.api.core.event.data.tag.block;

import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public record BlockTagGenerationDescription(List<DeferredBlock<?>> blocks, Class<?> tagContainer) {
}