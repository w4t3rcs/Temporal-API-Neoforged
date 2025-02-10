package com.temporal.api.core.event.data.tag.item;

import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;

public record ItemTagGenerationDescription(List<DeferredItem<?>> items, Class<?> tagContainer) {
}