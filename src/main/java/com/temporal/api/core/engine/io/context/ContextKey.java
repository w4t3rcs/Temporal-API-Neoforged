package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.engine.io.IOLayer;

public final class ContextKey {
    private String name;
    private Class<?> clazz;
    private String modId;

    public ContextKey(Class<?> clazz) {
        this(clazz.getSimpleName(), clazz);
    }

    public ContextKey(String name, Class<?> clazz) {
        this(name, clazz, IOLayer.NEO_MOD.getModId());
    }

    public ContextKey(String name, Class<?> clazz, String modId) {
        this.name = name;
        this.clazz = clazz;
        this.modId = modId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }
}
