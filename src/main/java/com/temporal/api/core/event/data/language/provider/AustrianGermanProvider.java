package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class AustrianGermanProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalHashMap<>();

    public AustrianGermanProvider(PackOutput output) {
        super(output, "de_at");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}