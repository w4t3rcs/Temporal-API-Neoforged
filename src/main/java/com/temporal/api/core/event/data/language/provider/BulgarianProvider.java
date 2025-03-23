package com.temporal.api.core.event.data.language.provider;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class BulgarianProvider extends ApiLanguageProvider {
    public static final Map<String, String> TRANSLATIONS = new TemporalHashMap<>();

    public BulgarianProvider(PackOutput output) {
        super(output, "bg_bg");
    }

    @Override
    public Map<String, String> getTranslations() {
        return TRANSLATIONS;
    }
}