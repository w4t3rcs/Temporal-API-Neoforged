package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class PortugueseProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalHashMap<>();

    public PortugueseProvider(PackOutput output) {
        super(output, "pt_pt");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}