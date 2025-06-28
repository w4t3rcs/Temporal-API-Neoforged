package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.exception.ContextCreationException;

import java.util.HashMap;
import java.util.Map;

public class InjectionContextContainer implements ContextContainer<String, InjectionContext> {
    private static volatile InjectionContextContainer instance;
    private final Map<String, InjectionContext> contexts = new HashMap<>();

    @Override
    public InjectionContext getContext(String modId) {
        return contexts.get(modId);
    }

    @Override
    public InjectionContext createContext(String modId) {
        if (contexts.containsKey(modId)) throw new ContextCreationException("Context already exists!");
        contexts.put(modId, new InjectionContext());
        return getContext(modId);
    }

    @Override
    public void removeContext(String modId) {
        contexts.remove(modId);
    }

    public static InjectionContextContainer getInstance() {
        if (instance == null) {
            synchronized (InjectionContextContainer.class) {
                if (instance == null) {
                    instance = new InjectionContextContainer();
                }
            }
        }

        return instance;
    }
}
