package com.temporal.api.core.engine.io.metadata.strategy.field.data.biome;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.biome.TreeGeneration;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.biome.dto.Tree;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.lang.reflect.Field;

public class TreeGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(TreeGeneration.class)) {
            field.setAccessible(true);
            ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey = (ResourceKey<ConfiguredFeature<?, ?>>) field.get(object);
            TreeGeneration treeGeneration = field.getDeclaredAnnotation(TreeGeneration.class);
            Class<?> tagContainer = treeGeneration.biomeTagContainer();
            if (!tagContainer.equals(Object.class)) BiomeTagDynamicPreparer.TAG_CONTAINERS.add(tagContainer);
            TreeGeneration.Trunk trunk = treeGeneration.trunk();
            TreeGeneration.Foliage foliage = treeGeneration.foliage();
            TreeGeneration.FeatureSize featureSize = treeGeneration.featureSize();
            var configuration = new Tree.Configuration(treeGeneration.logBlockId(), treeGeneration.leavesBlockId(), treeGeneration.rootBlockId(),
                    trunk.trunkPlacerClass(), trunk.baseHeight(), trunk.heightRandA(), trunk.heightRandB(),
                    foliage.foliagePlacerClass(), foliage.radius(), foliage.offset(), foliage.height(),
                    featureSize.type(), featureSize.limit(), featureSize.upperLimit(),
                    featureSize.lowerSize(), featureSize.middleSize(), featureSize.upperSize(), featureSize.minClippedHeight(),
                    treeGeneration.ignoreVines());
            var placement = new Tree.Placement(treeGeneration.saplingBlockId(), treeGeneration.baseValue(), treeGeneration.chance(), treeGeneration.addedAmount());
            var biomeModifier = new Tree.BiomeModifier(treeGeneration.biomeTag());
            Tree tree = new Tree(IOHelper.getResourceId(configuredFeatureKey), configuration, placement, biomeModifier);
            GenerationFeaturesDescriptionContainer.TREES.put(configuredFeatureKey, tree);
        }
    }
}
