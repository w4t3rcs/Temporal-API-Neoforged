package com.temporal.api.core.event.data.advancement;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ApiAdvancementProvider implements AdvancementSubProvider {
    public static final List<AdvancementGenerationHolder> ADVANCEMENTS = new ArrayList<>();

    @Override
    public void generate(@NotNull HolderLookup.Provider provider, @NotNull Consumer<AdvancementHolder> consumer) {
        ADVANCEMENTS.forEach(advancement -> {
            Advancement.Builder builder = Advancement.Builder.advancement();
            builder.parent(AdvancementSubProvider.createPlaceholder(advancement.getParentRoot()));
            builder.display(advancement.icon(),
                    Component.translatable(advancement.getId() + ".title"), Component.translatable(advancement.getId() + ".description"),
                    advancement.getBackground(), advancement.getType(),
                    advancement.showToast(), advancement.isAnnouncedInChat(), advancement.isHidden()
            );

            builder.rewards(AdvancementRewards.Builder.experience(advancement.getExperience()));
            Pair<String, Criterion<?>> criterion = advancement.getCriterion();
            builder.addCriterion(criterion.getA(), criterion.getB());
            builder.requirements(advancement.getRequirements());
            builder.save(consumer, IOHelper.createResourceLocation(advancement.getId()));
        });
    }
}
