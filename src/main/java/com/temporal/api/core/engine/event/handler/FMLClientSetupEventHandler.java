package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.event.client.BowClientSetupCommand;
import com.temporal.api.core.event.client.ClientSetupCommand;
import com.temporal.api.core.event.client.CrossbowClientSetupCommand;
import com.temporal.api.core.event.client.ShieldClientSetupCommand;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public class FMLClientSetupEventHandler implements EventHandler {
    public static final List<DeferredItem<?>> BOWS = new ArrayList<>();
    public static final List<DeferredItem<?>> CROSSBOWS = new ArrayList<>();
    public static final List<DeferredItem<?>> SHIELDS = new ArrayList<>();
    private static final ClientSetupCommand<DeferredItem<?>> BOW_COMMAND = new BowClientSetupCommand();
    private static final ClientSetupCommand<DeferredItem<?>> CROSSBOW_COMMAND = new CrossbowClientSetupCommand();
    private static final ClientSetupCommand<DeferredItem<?>> SHIELD_COMMAND = new ShieldClientSetupCommand();

    @Override
    public void handle() {
        subscribeEvent(FMLClientSetupEvent.class, event -> {
            event.enqueueWork(() -> {
                BOW_COMMAND.execute(BOWS);
                CROSSBOW_COMMAND.execute(CROSSBOWS);
                SHIELD_COMMAND.execute(SHIELDS);
            });
        });
    }
}
