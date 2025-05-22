package com.temporal.api.core.util.other;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class TagUtils {
    public static TagKey<BannerPattern> createBannerPatternTag(String name) {
        return createTag(Registries.BANNER_PATTERN, name);
    }

    public static TagKey<BiomeModifier> createBiomeModifierTag(String name) {
        return createTag(NeoForgeRegistries.Keys.BIOME_MODIFIERS, name);
    }

    public static TagKey<Biome> createBiomeTag(String name) {
        return createTag(Registries.BIOME, name);
    }

    public static TagKey<Block> createBlockTag(String name) {
        return BlockTags.create(ResourceUtils.createResourceLocation(name));
    }

    public TagKey<EntityType<?>> createEntityTypeTag(String name) {
        return createTag(Registries.ENTITY_TYPE, name);
    }

    public static TagKey<Fluid> createFluidTag(String name) {
        return FluidTags.create(ResourceUtils.createResourceLocation(name));
    }

    public static TagKey<Item> createItemTag(String name) {
        return ItemTags.create(ResourceUtils.createResourceLocation(name));
    }

    public static TagKey<Structure> createStructureTag(String name) {
        return createTag(Registries.STRUCTURE, name);
    }

    public static <T> TagKey<T> createTag(ResourceKey<? extends Registry<T>> registry, String name) {
        return TagKey.create(registry, ResourceUtils.createResourceLocation(name));
    }
}
