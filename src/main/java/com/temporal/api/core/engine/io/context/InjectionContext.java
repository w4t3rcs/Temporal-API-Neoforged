package com.temporal.api.core.engine.io.context;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectionContext implements Context {
    private static volatile InjectionContext instance;
    private final Map<Class<?>, Object> objects;

    private InjectionContext() {
        this.objects = new HashMap<>();
    }

    @Override
    public <T> T getObject(Class<? extends T> key) {
        Object object = this.objects.get(key);
        if (object == null) {
            List<? extends T> list = this.getObjects(key);
            if (!list.isEmpty()) object = list.getFirst();
        }

        return key.cast(object);
    }

    @Override
    public <T> List<? extends T> getObjects(Class<T> commonInterface) {
        return this.objects.values()
                .stream()
                .filter(commonInterface::isInstance)
                .map(commonInterface::cast)
                .toList();
    }

    @Override
    public List<?> getAllObjects() {
        return this.objects.values()
                .stream()
                .toList();
    }

    @Override
    public <T> void putObject(Class<? extends T> key) {
        try {
            Constructor<? extends T> constructor = key.getDeclaredConstructor();
            T value = constructor.newInstance();
            this.putObject(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void putObject(T value) {
        this.putObject(value.getClass(), value);
    }

    @Override
    public <T> void putObject(Class<? extends T> key, T value) {
        this.objects.put(key, value);
    }

    public static <T> T getFromInstance(Class<? extends T> key) {
        InjectionContext context = InjectionContext.getInstance();
        return context.getObject(key);
    }

    public static InjectionContext getInstance() {
        if (instance == null) {
            synchronized (InjectionContext.class) {
                if (instance == null) {
                    instance = new InjectionContext();
                }
            }
        }

        return instance;
    }
}
