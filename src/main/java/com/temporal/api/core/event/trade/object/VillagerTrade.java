package com.temporal.api.core.event.trade.object;

public class VillagerTrade extends Trade {
    private final VillagerTradeDescription tradeDescription;

    public VillagerTrade(TradingItemHolder holder1, TradingItemHolder holder2, VillagerTradeDescription tradeDescription) {
        super(holder1, holder2);
        this.tradeDescription = tradeDescription;
    }

    public VillagerTradeDescription getTradeDescription() {
        return tradeDescription;
    }
}
