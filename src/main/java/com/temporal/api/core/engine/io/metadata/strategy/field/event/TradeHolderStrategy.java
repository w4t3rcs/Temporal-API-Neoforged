package com.temporal.api.core.engine.io.metadata.strategy.field.event;

import com.temporal.api.core.engine.event.handler.TradesEventHandler;
import com.temporal.api.core.engine.io.metadata.annotation.event.TradeHolder;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.trade.object.Trade;

import java.lang.reflect.Field;

public class TradeHolderStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(TradeHolder.class)) {
            field.setAccessible(true);
            Trade trade = (Trade) field.get(object);
            TradesEventHandler.TRADES.add(trade);
        }
    }
}
