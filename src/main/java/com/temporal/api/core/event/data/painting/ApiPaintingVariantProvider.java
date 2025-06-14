package com.temporal.api.core.event.data.painting;

import com.temporal.api.core.collection.TemporalArrayDeque;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.Optional;
import java.util.Queue;

public class ApiPaintingVariantProvider implements PaintingVariantProvider {
    public static final Queue<PaintingHolder> PAINTINGS = new TemporalArrayDeque<>();

    @Override
    public void registerPaintingVariant(BootstrapContext<PaintingVariant> context) {
        PAINTINGS.forEach(description -> {
            ResourceKey<PaintingVariant> variant = description.paintingVariant();
            String descriptionId = Util.makeDescriptionId("painting", variant.location());
            MutableComponent titleTranslation = Component.translatable(descriptionId + ".title").withStyle(ChatFormatting.YELLOW);
            context.register(variant, new PaintingVariant(
                    description.width(), description.height(), variant.location(),
                    Optional.of(titleTranslation),
                    description.hasAuthor() ? Optional.of(Component.translatable(descriptionId + ".author").withStyle(ChatFormatting.GRAY)) : Optional.empty()
            ));
        });
    }

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        PaintingVariantProvider provider = new ApiPaintingVariantProvider();
        provider.registerPaintingVariant(context);
    }
}
