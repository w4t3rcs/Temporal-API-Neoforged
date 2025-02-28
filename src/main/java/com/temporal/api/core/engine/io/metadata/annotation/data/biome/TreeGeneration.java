package com.temporal.api.core.engine.io.metadata.annotation.data.biome;

import com.temporal.api.core.engine.io.metadata.constant.TreeFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TreeGeneration {
    String logBlock();
    String leavesBlock();
    String rootBlock() default "grass";
    Class<? extends TrunkPlacer> trunkPlacerClass() default ForkingTrunkPlacer.class;
    int baseHeight();
    int heightRandA();
    int heightRandB();
    Class<? extends FoliagePlacer> foliagePlacerClass() default BlobFoliagePlacer.class;
    int radius();
    int offset();
    int height();
    TreeFeatureSize featureSize() default TreeFeatureSize.TWO_LAYERED;
    int limit();
    int upperLimit() default 0;
    int lowerSize();
    int middleSize() default 0;
    int upperSize();
    int minClippedHeight() default 0;
    boolean ignoreVines() default true;
    int baseValue();
    int chance();
    int addedAmount();
    String biomeTag() default "is_overworld";
    Class<?> biomeTagContainer() default Object.class;
}
