package com.temporal.api.core.event.data.language;

import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PolishProvider extends ApiLanguageProvider {
    public static final Map<Supplier<? extends Item>, String> ITEM_TRANSLATIONS = new HashMap<>();
    public static final Map<Supplier<? extends Block>, String> BLOCK_TRANSLATIONS = new HashMap<>();
    public static final Map<Supplier<? extends EntityType<?>>, String> ENTITY_TRANSLATIONS = new HashMap<>();
    public static final Map<Supplier<? extends MobEffect>, String> EFFECT_TRANSLATIONS = new HashMap<>();
    public static final Map<Supplier<? extends Enchantment>, String> ENCHANTMENT_TRANSLATIONS = new HashMap<>();
    public static final Map<String, String> OTHER_TRANSLATIONS = new HashMap<>();

    public PolishProvider(PackOutput output) {
        super(output, "pl_pl");
    }

    @Override
    public Map<Supplier<? extends Item>, String> getItemTranslations() {
        return ITEM_TRANSLATIONS;
    }

    @Override
    public Map<Supplier<? extends Block>, String> getBlockTranslations() {
        return BLOCK_TRANSLATIONS;
    }

    @Override
    public Map<Supplier<? extends EntityType<?>>, String> getEntityTranslations() {
        return ENTITY_TRANSLATIONS;
    }

    @Override
    public Map<Supplier<? extends MobEffect>, String> getEffectTranslations() {
        return EFFECT_TRANSLATIONS;
    }

    @Override
    public Map<Supplier<? extends Enchantment>, String> getEnchantmentTranslations() {
        return ENCHANTMENT_TRANSLATIONS;
    }

    @Override
    public Map<String, String> getOtherTranslations() {
        return OTHER_TRANSLATIONS;
    }
}