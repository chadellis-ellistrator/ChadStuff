package com.chadellis.stockmarket;

import java.time.LocalDateTime;

import com.chadellis.stockmarket.utils.Utils;

public class Trade {
    private final LocalDateTime executed = LocalDateTime.now();
    private final String symbol;
    private final TradeAction tradeAction;
    private final Float price;
    private final int quantity;

    public Trade(Quote quote, int quantity) {
        this.symbol = quote.getSymbol();
        this.tradeAction = quote.getTradeAction();
        this.price = quote.getPrice();
        this.quantity = quantity;
    }
    public String toString() {
        // StringBuilder ?
        final StringBuilder sb = new StringBuilder();
        sb.append(this.executed.format(Utils.customFormatter) + ": ");
        sb.append(this.tradeAction.name() + " ");
        sb.append(this.quantity + " ");
        sb.append(this.symbol + " @ ");
        sb.append(this.price);
        return sb.toString();
    }
}
