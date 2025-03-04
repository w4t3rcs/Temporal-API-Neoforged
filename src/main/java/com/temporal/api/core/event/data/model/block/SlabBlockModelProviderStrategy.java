package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.apache.commons.lang3.StringUtils;

public class SlabBlockModelProviderStrategy extends AbstractModelProviderStrategy<SlabBlock> {
    @Override
    public void registerBlockModel(DeferredBlock<SlabBlock> blockRegistry, BlockModelGenerators blockModels) {
        SlabBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation fullTexture = ResourceUtils.createResourceLocation(StringUtils.substringBefore(path, "_slab"));
        ResourceLocation upperTexture = ResourceUtils.createResourceLocation(path + "_top");
        ResourceLocation lowerTexture = ResourceUtils.createResourceLocation(path + "_bottom");
        BlockStateGenerator generator = BlockModelGenerators.createSlab(block, fullTexture, upperTexture, lowerTexture);
        blockModels.blockStateOutput.accept(generator);
    }
}
