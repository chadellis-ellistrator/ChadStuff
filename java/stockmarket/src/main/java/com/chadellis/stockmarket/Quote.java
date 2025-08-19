package com.chadellis.stockmarket;

public class Quote {
    private final String symbol;
    private final Float price;
    private final TradeAction tradeAction;
    private final int initialQuantity;
    private int remainingQuantity;
    private int id;

    public Quote(String symbol, Float price, int quantity, TradeAction tradeAction) {
        this.symbol = symbol;
        this.price = price;
        this.initialQuantity = quantity;
        this.remainingQuantity = quantity;
        this.tradeAction = tradeAction;
    }

    public Quote(String symbol, Float price, int quantity, TradeAction tradeAction, int id) {
        this.symbol = symbol;
        this.price = price;
        this.initialQuantity = quantity;
        this.remainingQuantity = quantity;
        this.tradeAction = tradeAction;
        this.id = id;
    }

    public void reduceShares(int quantity) {
        this.remainingQuantity -= quantity;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Float getPrice() {
        return this.price;
    }

    public int getInitialQuantity() {
        return this.initialQuantity;
    }

    public int getRemainingQuantity() {
        return this.remainingQuantity;
    }

    public TradeAction getTradeAction() {
        return this.tradeAction;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
       return this.getTradeAction().name() + " " + this.getRemainingQuantity() + " " + this.getSymbol() + " @ " + this.getPrice();
    }
}
