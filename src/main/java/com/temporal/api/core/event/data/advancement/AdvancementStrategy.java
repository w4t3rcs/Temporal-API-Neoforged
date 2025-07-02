package com.temporal.api.core.event.data.advancement;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface AdvancementStrategy {
    void generateAdvancement(@NotNull AdvancementGenerationHolder advancement, @NotNull HolderLookup.Provider provider, @NotNull Consumer<AdvancementHolder> consumer);

    default Advancement.Builder createBuilder() {
        return Advancement.Builder.advancement();
    }

    default void setParentRoot(Advancement.Builder builder, String parentRoot) {
        builder.parent(AdvancementSubProvider.createPlaceholder(parentRoot));
    }

    default void saveAdvancement(@NotNull Advancement.Builder builder, @NotNull Consumer<AdvancementHolder> consumer, String id) {
        builder.save(consumer, ResourceUtils.createResourceLocation(id).toString());
    }
}
