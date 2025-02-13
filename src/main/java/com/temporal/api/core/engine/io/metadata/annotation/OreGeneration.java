package com.temporal.api.core.engine.io.metadata.annotation;

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
        String registry();
        String[] replaceableBlocks();
        int size() default 1;
    }

    @interface Placement {
        Rarity rarity() default Rarity.COMMON;
        int count() default 1;
        Shape shape() default Shape.UNIFORM;
        int from();
        int to();

        enum Rarity {
            COMMON,
            RARE
        }

        enum Shape {
            TRIANGLE,
            UNIFORM
        }
    }

    @interface BiomeModifier {
        String biomeTag() default "is_overworld";
        Class<?> biomeTagContainer() default Object.class;
    }
}
