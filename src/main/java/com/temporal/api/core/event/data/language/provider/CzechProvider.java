package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class CzechProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalHashMap<>();

    public CzechProvider(PackOutput output) {
        super(output, "cs_cz");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}