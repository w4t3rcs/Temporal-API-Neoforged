package com.temporal.api.core.event.data.model.block;

import com.temporal.api.common.block.ApiCropBlock;
import com.temporal.api.core.engine.io.IOLayer;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ApiBlockModelProvider extends BlockStateProvider {
    private static final BlockModelProviderStrategyConsumer CONSUMER = new BlockModelProviderStrategyConsumerImpl();

    public ApiBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IOLayer.NEO_MOD.getModId(), existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        CONSUMER.registerModels(this);
    }

    public void blockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    public void blockItem(Block block) {
        blockItem(block, "");
    }

    public void blockItem(Block block, String suffix) {
        String locationFormat = "%s%s";
        String location = locationFormat.formatted(this.getBlockPath(block), suffix);
        this.simpleBlockItem(block, new ModelFile.UncheckedModelFile(location));
    }

    public <T extends Block> void familyMemberWithItem(T block, String removableSuffix, BiConsumer<T, ResourceLocation> blockModelRegistry, BiFunction<T, ResourceLocation, ModelFile> itemModelRegistry) {
        ResourceLocation parentTexture = this.getFamilyParentTexture(block, removableSuffix);
        blockModelRegistry.accept(block, parentTexture);
        this.simpleBlockItem(block, itemModelRegistry.apply(block, parentTexture));
    }

    public ResourceLocation getFamilyParentTexture(Block block, String removableSuffix) {
        String texturePath = this.getBlockPath(block).replace(removableSuffix, "");
        return ResourceLocation.parse(texturePath);
    }

    public ModelFile singleTextureItemModel(Block block) {
        return this.itemModels()
                .withExistingParent(BuiltInRegistries.ITEM.getKey(block.asItem()).toString(), "item/generated")
                .texture("layer0", this.getBlockPath(block));
    }

    public void cropBlock(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    @SuppressWarnings("unchecked")
    public <T extends ApiCropBlock> ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        Integer age = state.getValue(((T) block).getAgeProperty());
        models[0] = new ConfiguredModel(models().crop(modelName + age, ResourceUtils.createResourceLocation("block/" + textureName + age))
                .renderType("cutout"));
        return models;
    }

    public String getBlockPath(Block block) {
        return this.getBlockPath(block, "block");
    }

    public String getBlockPath(Block block, String prefix) {
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
        return location.getNamespace() + ":" + prefix + "/" + location.getPath();
    }
}
