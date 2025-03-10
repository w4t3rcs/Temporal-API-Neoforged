package com.temporal.api.core.event.data.model;

import com.temporal.api.core.engine.io.IOLayer;
import com.temporal.api.core.event.data.model.block.BlockModelProviderStrategyConsumer;
import com.temporal.api.core.event.data.model.block.BlockModelProviderStrategyConsumerImpl;
import com.temporal.api.core.event.data.model.item.ItemModelProviderStrategyConsumer;
import com.temporal.api.core.event.data.model.item.ItemModelProviderStrategyConsumerImpl;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class ModelProviderImpl extends ModelProvider {
    private static final ItemModelProviderStrategyConsumer itemModelConsumer = new ItemModelProviderStrategyConsumerImpl();
    private static final BlockModelProviderStrategyConsumer blockModelConsumer = new BlockModelProviderStrategyConsumerImpl();

    public ModelProviderImpl(PackOutput output) {
        super(output, IOLayer.NEO_MOD.getModId());
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        itemModelConsumer.registerModels(itemModels);
        blockModelConsumer.registerModels(blockModels);
    }
}
