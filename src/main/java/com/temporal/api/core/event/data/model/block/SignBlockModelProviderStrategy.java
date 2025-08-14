package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SignBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        StandingSignBlock block = (StandingSignBlock) blockRegistry.get();
        WallSignBlock wallBlock = (WallSignBlock) RegistryUtils.getBlockById(additionalData[0].toString());
        provider.signBlock(block, wallBlock, ResourceUtils.parse(additionalData[1].toString()));
    }
}
