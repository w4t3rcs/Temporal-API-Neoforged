package com.temporal.api.core.event.data.trim.pattern;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.equipment.trim.TrimPattern;

public interface TrimPatternProvider {
    void registerTrimPatterns(BootstrapContext<TrimPattern> context);
}
