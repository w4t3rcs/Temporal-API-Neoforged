package com.temporal.api.core.event.data.json;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.json.formatter.JsonFormatter;
import com.temporal.api.core.json.formatter.StringJsonFormatter;
import com.temporal.api.core.json.inserter.JsonInserter;
import com.temporal.api.core.json.inserter.ResourceInserter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.nio.file.Path;
import java.util.Queue;
import java.util.stream.Collectors;

public class PlaceablePaintingProvider implements JsonProvider {
    public static final Queue<ResourceKey<PaintingVariant>> PLACEABLES = new TemporalArrayDeque<>();
    private static final JsonFormatter<String> FORMATTER = new StringJsonFormatter();
    private static final String FORMAT = """
            {
              "values": [
                ${placeables}
              ]
            }
            """;
    private static final JsonInserter<String, Path> RESOURCE_INSERTER = new ResourceInserter();
    private static final String TARGET_FILE = "data/minecraft/tags/painting_variant/placeable.json";
    private static final Path TARGET_FILE_PATH = Path.of(TARGET_FILE);

    @Override
    public void registerFiles() {
        String placeablesString = PLACEABLES.stream()
                .map(ResourceKey::location)
                .map(location -> "\"" + location.getNamespace() + ":" + location.getPath() + "\"")
                .collect(Collectors.joining(",\n"));
        PLACEABLES.clear();
        String formatted = FORMATTER.format(FORMAT, placeablesString);
        RESOURCE_INSERTER.insert(formatted, TARGET_FILE_PATH);
    }
}
