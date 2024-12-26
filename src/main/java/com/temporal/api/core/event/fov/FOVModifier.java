package com.temporal.api.core.event.fov;

import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ComputeFovModifierEvent;

public interface FOVModifier {
    public void modify(ComputeFovModifierEvent event, Item... items);
}
