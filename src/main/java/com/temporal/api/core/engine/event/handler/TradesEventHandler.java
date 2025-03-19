package com.temporal.api.core.engine.event.handler;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.event.trade.SimpleTradeCustomizer;
import com.temporal.api.core.event.trade.TradeCustomizer;
import com.temporal.api.core.event.trade.object.Trade;
import com.temporal.api.core.event.trade.object.VillagerTrade;
import com.temporal.api.core.event.trade.object.WandererTrade;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.Queue;

public class TradesEventHandler implements EventHandler {
    public static final Queue<Trade> TRADES = new TemporalArrayDeque<>();

    @Override
    public void handle() {
        if (TRADES.isEmpty()) return;
        Queue<VillagerTrade> villagerTrades = new TemporalArrayDeque<>();
        Queue<WandererTrade> wandererTrades = new TemporalArrayDeque<>();
        init(villagerTrades, wandererTrades);
        TradeCustomizer customizer = new SimpleTradeCustomizer();
        subscribeVillagerTradesEvent(villagerTrades, customizer);
        subscribeWandererTradesEvent(wandererTrades, customizer);
    }

    private void init(Queue<VillagerTrade> villagerTrades, Queue<WandererTrade> wandererTrades) {
        TRADES.forEach(trade -> {
            if (trade instanceof VillagerTrade villagerTrade) {
                villagerTrades.add(villagerTrade);
            } else if (trade instanceof WandererTrade wandererTrade) {
                wandererTrades.add(wandererTrade);
            }
        });
    }

    private void subscribeVillagerTradesEvent(Queue<VillagerTrade> villagerTrades, TradeCustomizer customizer) {
        subscribeEvent(VillagerTradesEvent.class, event -> {
            villagerTrades.forEach(trade -> {
                customizer.customize(event, trade);
            });
        });
    }

    private void subscribeWandererTradesEvent(Queue<WandererTrade> wandererTrades, TradeCustomizer customizer) {
        subscribeEvent(WandererTradesEvent.class, event -> {
            wandererTrades.forEach(trade -> {
                customizer.customize(event, trade);
            });
        });
    }
}
