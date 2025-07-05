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
        String id = RegistryUtils.getIdFromBlock(block);
        WallSignBlock wallBlock = (WallSignBlock) RegistryUtils.getBlockById(id.replace("sign", "wall_sign"));
        provider.signBlock(block, wallBlock, ResourceUtils.parse(additionalData[0].toString()));
        String blockPath = provider.getBlockPath(block);
        provider.simpleBlockItem(block, provider.singleTextureItemModel(block, blockPath.replace("block/", "item/")));
    }
}
