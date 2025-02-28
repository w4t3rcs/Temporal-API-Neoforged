package com.temporal.api.core.engine.io.metadata.strategy.field.data.biome;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.biome.TreeGeneration;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.biome.dto.Tree;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class TreeGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(TreeGeneration.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            TreeGeneration treeGeneration = field.getDeclaredAnnotation(TreeGeneration.class);
            Class<?> tagContainer = treeGeneration.biomeTagContainer();
            if (!tagContainer.equals(Object.class)) BiomeTagDynamicPreparer.TAG_CONTAINERS.add(tagContainer);
            var configuration = new Tree.Configuration(treeGeneration.logBlock(), treeGeneration.leavesBlock(), treeGeneration.rootBlock(),
                    treeGeneration.trunkPlacerClass(), treeGeneration.baseHeight(), treeGeneration.heightRandA(), treeGeneration.heightRandB(),
                    treeGeneration.foliagePlacerClass(), treeGeneration.radius(), treeGeneration.offset(), treeGeneration.height(),
                    treeGeneration.featureSize(), treeGeneration.limit(), treeGeneration.upperLimit(),
                    treeGeneration.lowerSize(), treeGeneration.middleSize(), treeGeneration.upperSize(), treeGeneration.minClippedHeight(),
                    treeGeneration.ignoreVines());
            var placement = new Tree.Placement(treeGeneration.baseValue(), treeGeneration.chance(), treeGeneration.addedAmount());
            var biomeModifier = new Tree.BiomeModifier(treeGeneration.biomeTag());
            Tree tree = new Tree(IOHelper.getResourceId(registryObject.getKey()), configuration, placement, biomeModifier);
            GenerationFeaturesDescriptionContainer.TREES.put(registryObject, tree);
        }
    }
}
