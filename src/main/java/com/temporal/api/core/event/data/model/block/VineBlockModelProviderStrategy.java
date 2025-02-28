package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.VineBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class VineBlockModelProviderStrategy extends AbstractModelProviderStrategy<VineBlock> {
    public VineBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<VineBlock> blockRegistry) {
        final VineBlock block = blockRegistry.get();
        BlockModelGenerators blockModels = this.getBlockModels();
        blockModels.createMultifaceBlockStates(block);
        ResourceLocation resourcelocation = blockModels.createFlatItemModelWithBlockTexture(IOHelper.getItemById(IOHelper.getIdFromBlock(block)), block);
        blockModels.registerSimpleItemModel(block, resourcelocation);
    }
}
