package com.temporal.api.core.event.data.language;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;

public class JapaneseProvider extends ApiLanguageProvider {
    public static final Map<Supplier<? extends Item>, String> ITEM_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<Supplier<? extends Block>, String> BLOCK_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<Supplier<? extends EntityType<?>>, String> ENTITY_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<Supplier<? extends MobEffect>, String> EFFECT_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<ResourceKey<Enchantment>, String> ENCHANTMENT_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<ResourceKey<TrimMaterial>, String> TRIM_MATERIAL_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<ResourceKey<TrimPattern>, String> TRIM_PATTERN_TRANSLATIONS = new TemporalHashMap<>();
    public static final Map<String, String> OTHER_TRANSLATIONS = new TemporalHashMap<>();

    public JapaneseProvider(PackOutput output) {
        super(output, "ja_jp");
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
    public Map<ResourceKey<Enchantment>, String> getEnchantmentTranslations() {
        return ENCHANTMENT_TRANSLATIONS;
    }

    @Override
    public Map<ResourceKey<TrimMaterial>, String> getTrimMaterialTranslations() {
        return TRIM_MATERIAL_TRANSLATIONS;
    }

    @Override
    public Map<ResourceKey<TrimPattern>, String> getTrimPatternTranslations() {
        return TRIM_PATTERN_TRANSLATIONS;
    }

    @Override
    public Map<String, String> getOtherTranslations() {
        return OTHER_TRANSLATIONS;
    }
}