package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class AbstractFamilyBlockModelProviderStrategy extends AbstractModelProviderStrategy {
    protected BlockModelGenerators.BlockFamilyProvider createFamilyProvider(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels, String target) {
        Block block = blockRegistry.get();
        return blockModels.familyWithExistingFullBlock(getParentBlock(block, target, ""));
    }

    protected BlockModelGenerators.BlockFamilyProvider createFamilyProvider(DeferredBlock<?> blockRegistry, BlockModelGenerators blockModels, String target, String replacement) {
        Block block = blockRegistry.get();
        return blockModels.familyWithExistingFullBlock(getParentBlock(block, target, replacement));
    }

    protected Block getParentBlock(Block childBlock, String target, String replacement) {
        String parentBlockId = RegistryUtils.getIdFromBlock(childBlock).replace(target, replacement);
        return RegistryUtils.getBlockById(parentBlockId);
    }
}
