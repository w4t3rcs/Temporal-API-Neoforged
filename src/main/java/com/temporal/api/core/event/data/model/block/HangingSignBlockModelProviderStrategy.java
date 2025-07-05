package com.temporal.api.core.event.data.model.block;

import com.temporal.api.core.util.other.RegistryUtils;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class HangingSignBlockModelProviderStrategy implements BlockModelProviderStrategy {
    @Override
    public void registerBlockModel(DeferredBlock<?> blockRegistry, ApiBlockModelProvider provider, Object... additionalData) {
        CeilingHangingSignBlock block = (CeilingHangingSignBlock) blockRegistry.get();
        String id = RegistryUtils.getIdFromBlock(block);
        WallHangingSignBlock wallBlock = (WallHangingSignBlock) RegistryUtils.getBlockById(id.replace("hanging", "wall_hanging"));
        provider.hangingSignBlock(block, wallBlock, ResourceUtils.parse(additionalData[0].toString()));
        provider.simpleBlockItem(block, provider.singleTextureItemModel(block));
    }
}
