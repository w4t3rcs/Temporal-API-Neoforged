package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WallBlockModelProviderStrategy extends AbstractModelProviderStrategy<WallBlock> {
    public WallBlockModelProviderStrategy(BlockModelGenerators blockModels) {
        super(blockModels);
    }

    @Override
    public void registerBlockModel(DeferredBlock<WallBlock> blockRegistry) {
        WallBlock block = blockRegistry.get();
        String path = this.getBlockPath(blockRegistry);
        ResourceLocation postTexture = ResourceUtils.createResourceLocation(path + "_post");
        ResourceLocation lowTexture = ResourceUtils.createResourceLocation(path + "_low");
        ResourceLocation tallTexture = ResourceUtils.createResourceLocation(path + "_tall");
        BlockStateGenerator generator = BlockModelGenerators.createWall(block, postTexture, lowTexture, tallTexture);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
