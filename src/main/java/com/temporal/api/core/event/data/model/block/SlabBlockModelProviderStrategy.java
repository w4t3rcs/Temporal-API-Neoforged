package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.apache.commons.lang3.StringUtils;

public class SlabBlockModelProviderStrategy extends AbstractModelProviderStrategy<SlabBlock> {
    public SlabBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<SlabBlock> blockRegistry) {
        SlabBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation fullTexture = IOHelper.createResourceLocation(StringUtils.substringBefore(path, "_slab"));
        ResourceLocation upperTexture = IOHelper.createResourceLocation(path + "_top");
        ResourceLocation lowerTexture = IOHelper.createResourceLocation(path + "_bottom");
        BlockStateGenerator generator = BlockModelGenerators.createSlab(block, fullTexture, upperTexture, lowerTexture);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
