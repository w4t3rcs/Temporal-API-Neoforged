package com.temporal.api.core.engine.io.metadata;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DefaultAnnotationStrategyExecutor implements AnnotationStrategyExecutor {
    private static volatile DefaultAnnotationStrategyExecutor instance;

    private DefaultAnnotationStrategyExecutor() {
    }

    @Override
    public void executeClass(ClassAnnotationStrategy strategy, Class<?> clazz) {
        try {
            strategy.execute(clazz, null);
            logScanning(strategy, clazz);
        } catch (Exception e) {
            logException(e, strategy, clazz);
        }
    }

    @Override
    public void executeStaticField(FieldAnnotationStrategy strategy, Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                strategy.execute(field, null);
            }

            logScanning(strategy, clazz);
        } catch (Exception e) {
            logException(e, strategy, clazz);
        }
    }

    @Override
    public void executeField(FieldAnnotationStrategy strategy, Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                strategy.execute(field, InjectionContext.getFromInstance(clazz));
            }

            logScanning(strategy, clazz);
        } catch (Exception e) {
            logException(e, strategy, clazz);
        }
    }

    @Override
    public void executeMethod(MethodAnnotationStrategy strategy, Class<?> clazz) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                strategy.execute(method, InjectionContext.getFromInstance(clazz));
            }

            logScanning(strategy, clazz);
        } catch (Exception e) {
            logException(e, strategy, clazz);
        }
    }

    private void logScanning(ObjectStrategy<?> strategy, Class<?> clazz) {
        ApiMod.LOGGER.info("Scanned: strategy - {}, class - {}", strategy.getClass().getSimpleName(), clazz.getSimpleName());
    }

    private void logException(Exception e, ObjectStrategy<?> strategy, Class<?> clazz) {
        ApiMod.LOGGER.warn("{} in {} went wrong! - {}", strategy.getClass().getSimpleName(), clazz.getSimpleName(), e.getMessage());
    }

    public static AnnotationStrategyExecutor getInstance() {
        if (instance == null) {
            synchronized (DefaultAnnotationStrategyExecutor.class) {
                if (instance == null) {
                    instance = new DefaultAnnotationStrategyExecutor();
                }
            }
        }

        return instance;
    }
}
