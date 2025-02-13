package com.temporal.api.core.event.data.advancement;

import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;

public interface AdvancementGenerationHolder {
    String getId();

    String getParentRoot();

    ItemLike icon();

    @Nullable
    default ResourceLocation getBackground() {
        return null;
    }

    AdvancementType getType();

    boolean showToast();

    boolean isAnnouncedInChat();

    boolean isHidden();

    int getExperience();

    Pair<String, Criterion<?>> getCriterion();

    AdvancementRequirements getRequirements();
}
