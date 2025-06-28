package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.engine.io.IOLayer;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class InjectionContext implements Context {
    private final Map<ContextKey, Object> objects;

    protected InjectionContext() {
        this.objects = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(String key) {
        ContextKey contextKey = this.getContextKey(key);
        return (T) this.objects.get(contextKey);
    }

    @Override
    public <T> T getObject(Class<? extends T> key) {
        Object object = this.objects.get(this.getContextKey(key));
        if (object == null) {
            List<? extends T> list = this.getObjects(key);
            if (!list.isEmpty()) object = list.getFirst();
        }

        return key.cast(object);
    }

    @Override
    public ContextKey getContextKey(String name) {
        return this.getContextKey(key -> name.equals(key.getName()));
    }

    @Override
    public ContextKey getContextKey(Class<?> clazz) {
        return this.getContextKey(key -> clazz.equals(key.getClazz()));
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
    public <T> void putObject(String key, T value) {
        this.putObject(new ContextKey(key, value.getClass()), value);
    }

    @Override
    public <T> void putObject(Class<? extends T> key, T value) {
        this.putObject(new ContextKey(key), value);
    }

    @Override
    public <T> void putObject(ContextKey key, T value) {
        this.objects.put(key, value);
    }

    @Override
    public <T> void removeObject(Class<? extends T> key) {
        this.objects.remove(this.getContextKey(key));
    }

    @Override
    public <T> void removeObject(T value) {
        this.objects.remove(this.getContextKey(value.getClass()));
    }

    @Override
    public void removeAllObjects() {
        this.objects.clear();
    }

    private ContextKey getContextKey(Predicate<? super ContextKey> predicate) {
        return this.objects.keySet()
                .stream()
                .filter(predicate)
                .findAny()
                .orElse(null);
    }

    public static <T> T getFromInstance(String key) {
        Context context = InjectionContext.getInstance();
        return context.getObject(key);
    }

    public static <T> T getFromInstance(Class<? extends T> key) {
        Context context = InjectionContext.getInstance();
        return context.getObject(key);
    }

    public static InjectionContext getInstance() {
        return InjectionContextContainer.getInstance()
                .getContext(IOLayer.NEO_MOD.getModId());
    }
}
