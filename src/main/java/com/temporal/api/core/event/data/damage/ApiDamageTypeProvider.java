package com.temporal.api.core.event.data.damage;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.HashMap;
import java.util.Map;

public class ApiDamageTypeProvider implements DamageTypeProvider {
    public static final Map<ResourceKey<DamageType>, DamageTypeDescriptionHolder> DAMAGE_TYPES = new HashMap<>();

    @Override
    public void registerDamageTypes(BootstrapContext<DamageType> context) {
        DAMAGE_TYPES.forEach((damageType, description) -> {
            String messageId = damageType.location().getNamespace() + "." + damageType.location().getPath();
            context.register(damageType, new DamageType(messageId,
                    description.damageScaling(), description.exhaustion(), description.effects(), description.messageType()));
        });
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        DamageTypeProvider damageTypeProvider = new ApiDamageTypeProvider();
        damageTypeProvider.registerDamageTypes(context);
    }
}
