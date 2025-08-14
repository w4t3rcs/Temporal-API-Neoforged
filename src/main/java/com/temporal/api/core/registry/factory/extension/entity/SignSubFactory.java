package com.temporal.api.core.registry.factory.extension.entity;

import com.temporal.api.core.engine.event.handler.EntityRendererRegisterRendererEventHandler;
import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockEntityTypeFactory;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public interface SignSubFactory {
    default DeferredHolder<BlockEntityType<?>, BlockEntityType<SignBlockEntity>> createSign(String name, Supplier<StandingSignBlock> standingSignBlock, Supplier<WallSignBlock> wallSignBlock) {
        BlockEntityTypeFactory factory = InjectionPool.getFromInstance(BlockEntityTypeFactory.class);
        DeferredHolder<BlockEntityType<?>, BlockEntityType<SignBlockEntity>> holder = factory.create(name, SignBlockEntity::new, null, standingSignBlock.get(), wallSignBlock.get());
        EntityRendererRegisterRendererEventHandler.RENDERING_REGISTRIES.add(event -> event.registerBlockEntityRenderer(holder.value(), SignRenderer::new));
        return holder;
    }
}
