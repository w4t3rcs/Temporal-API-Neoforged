package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class DeferredRegisterContextInitializer implements ContextInitializer {
    @Override
    public void initialize(List<?> externalObjects) {
        Context context = InjectionContext.getInstance();
        context.putObject("items", RegistryUtils.createItemRegistry());
        context.putObject("blocks", RegistryUtils.createBlockRegistry());
        context.putObject("biomes", RegistryUtils.createRegistry(Registries.BIOME));
        context.putObject("creative_mode_tabs", RegistryUtils.createRegistry(Registries.CREATIVE_MODE_TAB));
        context.putObject("mob_effects", RegistryUtils.createRegistry(Registries.MOB_EFFECT));
        context.putObject("entity_types", RegistryUtils.createRegistry(Registries.ENTITY_TYPE));
        context.putObject("block_entity_types", RegistryUtils.createRegistry(Registries.BLOCK_ENTITY_TYPE));
        context.putObject("particle_types", RegistryUtils.createRegistry(Registries.PARTICLE_TYPE));
        context.putObject("poi_types", RegistryUtils.createRegistry(Registries.POINT_OF_INTEREST_TYPE));
        context.putObject("potions", RegistryUtils.createRegistry(Registries.POTION));
        context.putObject("recipe_serializers", RegistryUtils.createRegistry(Registries.RECIPE_SERIALIZER));
        context.putObject("sound_events", RegistryUtils.createRegistry(Registries.SOUND_EVENT));
        context.putObject("villager_professions", RegistryUtils.createRegistry(Registries.VILLAGER_PROFESSION));
        context.putObject("global_loot_modifier_serializers", RegistryUtils.createRegistry(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS));
        context.putObject("menu_types", RegistryUtils.createRegistry(Registries.MENU));
        context.putObject("trigger_types", RegistryUtils.createRegistry(Registries.TRIGGER_TYPE));
    }
}
