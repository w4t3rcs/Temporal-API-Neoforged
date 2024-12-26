package com.temporal.api.core.engine.io.resource;

import com.google.common.base.MoreObjects;
import net.minecraftforge.fml.common.Mod;

public class ForgeMod {
    private String name;
    private Class<?> clazz;
    private String modId;

    public ForgeMod(String name, Class<?> clazz, String modId) {
        this.name = name;
        this.clazz = clazz;
        this.modId = modId;
    }

    public static ForgeMod create(Class<?> clazz) {
        return new ForgeMod(clazz.getSimpleName(), clazz, clazz.getDeclaredAnnotation(Mod.class).value());
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("clazz", clazz)
                .add("modId", modId)
                .toString();
    }
}
