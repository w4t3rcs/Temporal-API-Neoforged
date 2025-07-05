package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class FilipinoProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalMap<>();

    public FilipinoProvider(PackOutput output) {
        super(output, "fil_ph");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}