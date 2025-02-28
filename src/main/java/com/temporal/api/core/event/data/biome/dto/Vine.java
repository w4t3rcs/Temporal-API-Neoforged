package com.temporal.api.core.event.data.biome.dto;

public record Vine(String id, Placement placement, BiomeModifier biomeModifier) {
    public record Placement(int count, int from, int to) {
    }

    public record BiomeModifier(String biomeTag) {
    }
}
