package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.engine.io.IOLayer;
import com.temporal.api.core.event.data.language.transformer.*;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Map;

public abstract class ApiLanguageProvider extends LanguageProvider {
    public static final KeyTransformer<ResourceKey<Item>> ITEM_TRANSFORMER = new ItemTransformer();
    public static final KeyTransformer<ResourceKey<Block>> BLOCK_TRANSFORMER = new BlockTransformer();
    public static final KeyTransformer<ResourceKey<Block>> BLOCK_ITEM_TRANSFORMER = new BlockItemTransformer();
    public static final KeyTransformer<ResourceKey<EntityType<?>>> ENTITY_TYPE_TRANSFORMER = new EntityTypeTransformer();
    public static final KeyTransformer<ResourceKey<MobEffect>> MOB_EFFECT_TRANSFORMER = new MobEffectTransformer();
    public static final KeyTransformer<ResourceKey<SoundEvent>> SOUND_EVENT_TRANSFORMER = new SoundEventTransformer();
    public static final KeyTransformer<ResourceKey<PaintingVariant>> PAINTING_TRANSFORMER = new PaintingTransformer();
    public static final KeyTransformer<ResourceKey<CreativeModeTab>> CREATIVE_MODE_TAB_TRANSFORMER = new CreativeModeTabTransformer();
    public static final KeyTransformer<ResourceKey<Enchantment>> ENCHANTMENT_TRANSFORMER = new EnchantmentTransformer();
    public static final KeyTransformer<ResourceKey<TrimMaterial>> TRIM_MATERIAL_TRANSFORMER = new TrimMaterialTransformer();
    public static final KeyTransformer<ResourceKey<TrimPattern>> TRIM_PATTERN_TRANSFORMER = new TrimPatternTransformer();
    public static final KeyTransformer<Component> COMPONENT_TRANSFORMER = new ComponentTransformer();
    public static final KeyTransformer<String> STRING_TRANSFORMER = new StringTransformer();

    public ApiLanguageProvider(PackOutput output, String locale) {
        super(output, IOLayer.NEO_MOD.getModId(), locale);
    }

    @Override
    protected void addTranslations() {
        this.getTranslations().forEach(this::add);
    }

    public abstract Map<String, String> getTranslations();
}
