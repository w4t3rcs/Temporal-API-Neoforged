package com.temporal.api.core.engine.io.metadata.annotation.data.other;

import com.temporal.api.core.engine.io.metadata.constant.OrePlacementShape;
import com.temporal.api.core.engine.io.metadata.constant.OreRarity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OreGeneration {
    Configuration configuration();
    Placement placement();
    BiomeModifier biomeModifier();

    @interface Configuration {
        String[] replaceableBlocks();
        int size() default 1;
    }

    @interface Placement {
        OreRarity rarity() default OreRarity.COMMON;
        int count() default 1;
        OrePlacementShape shape() default OrePlacementShape.UNIFORM;
        int from();
        int to();
    }

    @interface BiomeModifier {
        String biomeTag() default "is_overworld";
        Class<?> biomeTagContainer() default Object.class;
    }
}
