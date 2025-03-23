package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class SwissGermanProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalHashMap<>();

    public SwissGermanProvider(PackOutput output) {
        super(output, "de_ch");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}