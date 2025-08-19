package com.chadellis.stockmarket.events;

import com.chadellis.stockmarket.Quote;
import com.chadellis.stockmarket.TradeAction;

class TradeEvent {
    //private LocalDateTime created = LocalDateTime.now();
    private TradeAction tradeAction;
    private int quantity;
    private String symbol;
    private Float price;

    public TradeEvent(TradeAction tradeAction, Quote quote, int quantity) {
        this.tradeAction = tradeAction;
        this.quantity = quantity;
        this.symbol = quote.getSymbol();
        this.price = quote.getPrice();
    }

    public TradeEvent() {}

    public TradeAction getTradeAction() {
        return this.tradeAction;
    }

    public void setTradeAction(TradeAction tradeAction) {
        this.tradeAction = tradeAction;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String toString() {
        return this.getTradeAction().name() + " " + this.getQuantity() + " " + this.getSymbol() + " @ " + this.getPrice(); 
    }
}
