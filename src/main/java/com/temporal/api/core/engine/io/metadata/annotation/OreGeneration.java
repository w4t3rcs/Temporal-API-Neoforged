package com.temporal.api.core.engine.io.metadata.annotation;

import net.minecraft.tags.BiomeTags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OreGeneration {
    Configuration configuration();
    Placement placement();
    BiomeModifier biomeModifier();

    @interface Configuration {
        String registry() default "";
        String[] replaceableBlocks() default {};
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
        String biomeTag() default "";
        Class<?>[] biomeTagContainers() default {BiomeTags.class};
    }
}
