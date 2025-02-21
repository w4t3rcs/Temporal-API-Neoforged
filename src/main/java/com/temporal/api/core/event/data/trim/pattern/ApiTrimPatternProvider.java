package com.temporal.api.core.event.data.trim.pattern;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.TrimPattern;

import java.util.HashMap;
import java.util.Map;

public class ApiTrimPatternProvider implements TrimPatternProvider {
    public static final Map<ResourceKey<TrimPattern>, TrimPatternDescriptionHolder> TRIM_PATTERNS = new HashMap<>();

    @Override
    public void registerTrimPatterns(BootstrapContext<TrimPattern> context) {
        TRIM_PATTERNS.forEach((trimPattern, description) -> {
            ResourceLocation location = trimPattern.location();
            Holder<Item> itemHolder = IOHelper.getItemById(description.itemId()).getDefaultInstance().getItemHolder();
            String descriptionId = Util.makeDescriptionId("trim_pattern", location);
            MutableComponent component = Component.translatable(descriptionId);
            context.register(trimPattern, new TrimPattern(location, itemHolder, component, description.decal()));
        });
    }

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        TrimPatternProvider provider = new ApiTrimPatternProvider();
        provider.registerTrimPatterns(context);
    }
}
