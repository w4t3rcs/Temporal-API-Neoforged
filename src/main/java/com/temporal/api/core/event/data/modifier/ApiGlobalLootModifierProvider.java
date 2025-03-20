package com.temporal.api.core.event.data.modifier;

import com.temporal.api.common.loot.AddItemModifier;
import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.engine.io.IOLayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class ApiGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public static final Queue<ChestModifierDescription> CHEST_MODIFIER_DESCRIPTIONS = new TemporalArrayDeque<>();

    public ApiGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, IOLayer.NEO_MOD.getModId());
    }

    @Override
    protected void start() {
        CHEST_MODIFIER_DESCRIPTIONS.forEach(description -> {
            add(description.getModifierName(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(ResourceLocation.parse(description.getChestId())).build(),
                    LootItemRandomChanceCondition.randomChance(description.getChance()).build()
            }, description.getItem()));
        });
    }
}
