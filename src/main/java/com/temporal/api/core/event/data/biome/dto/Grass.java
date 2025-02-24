package com.temporal.api.core.event.data.biome.dto;

public record Grass(String id, Configuration configuration, Placement placement, BiomeModifier biomeModifier) {
    public record Configuration(int tries) {
    }

    public record Placement(int count) {
    }

    public record BiomeModifier(String... biomeTags) {
    }
}
