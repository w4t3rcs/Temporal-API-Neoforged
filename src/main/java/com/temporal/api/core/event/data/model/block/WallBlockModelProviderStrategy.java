package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.engine.io.IOHelper;
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
        ResourceLocation postTexture = IOHelper.createResourceLocation(path + "_post");
        ResourceLocation lowTexture = IOHelper.createResourceLocation(path + "_low");
        ResourceLocation tallTexture = IOHelper.createResourceLocation(path + "_tall");
        BlockStateGenerator generator = BlockModelGenerators.createWall(block, postTexture, lowTexture, tallTexture);
        this.getBlockModels().blockStateOutput.accept(generator);
    }
}
