package com.temporal.api.core.event.data.language;

import com.temporal.api.core.engine.io.IOLayer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Map;
import java.util.function.Supplier;

public abstract class ApiLanguageProvider extends LanguageProvider {
    public ApiLanguageProvider(PackOutput output, String locale) {
        super(output, IOLayer.NEO_MOD.getModId(), locale);
    }

    @Override
    protected void addTranslations() {
        translateItems();
        translateBlocks();
        translateEntities();
        translateEffects();
        translateEnchantments();
        translateTrimMaterials();
        translateTrimPatterns();
        translateOthers();
    }

    protected void translateItems() {
        this.getItemTranslations().forEach(this::addItem);
    }

    protected void translateBlocks() {
        this.getBlockTranslations().forEach((block, value) -> {
            this.addBlock(block, value);
            this.addItem(() -> block.get().asItem(), value);
        });
    }

    protected void translateEntities() {
        this.getEntityTranslations().forEach(this::addEntityType);
    }

    protected void translateEffects() {
        this.getEffectTranslations().forEach(this::addEffect);
    }

    protected void translateEnchantments() {
        this.getEnchantmentTranslations().forEach((key, value) -> translateResourceKey(key, value, "enchantment"));
    }

    protected void translateTrimMaterials() {
        this.getTrimMaterialTranslations().forEach((key, value) -> translateResourceKey(key, value, "trim_material"));
    }

    protected void translateTrimPatterns() {
        this.getTrimPatternTranslations().forEach((key, value) -> translateResourceKey(key, value, "trim_pattern"));
    }

    protected void translateResourceKey(ResourceKey<?> key, String value, String prefix) {
        ResourceLocation location = key.location();
        this.add(prefix + "." + location.getNamespace() + "." + location.getPath(), value);
    }

    protected void translateOthers() {
        this.getOtherTranslations().forEach(this::add);
    }

    public abstract Map<Supplier<? extends Item>, String> getItemTranslations();

    public abstract Map<Supplier<? extends Block>, String> getBlockTranslations();

    public abstract Map<Supplier<? extends EntityType<?>>, String> getEntityTranslations();

    public abstract Map<Supplier<? extends MobEffect>, String> getEffectTranslations();

    public abstract Map<ResourceKey<Enchantment>, String> getEnchantmentTranslations();

    public abstract Map<ResourceKey<TrimMaterial>, String> getTrimMaterialTranslations();

    public abstract Map<ResourceKey<TrimPattern>, String> getTrimPatternTranslations();

    public abstract Map<String, String> getOtherTranslations();
}
