package com.chadellis.stockmarket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.chadellis.stockmarket.db.QuoteTable;
import com.chadellis.stockmarket.events.EventConsumer;
import com.chadellis.stockmarket.events.EventPublisher;

public class Market {
    private final Map<String, List<Quote>> bids;
    private final Map<String, List<Quote>> asks;
    private static final List<String> validSymbols = Arrays.asList("PYPL", "AFRM");
    private final EventPublisher eventPublisher;
    private final EventConsumer eventConsumer;
    private final QuoteTable quoteTable;

    public Market() throws IOException {
        this.bids = new HashMap<>();
        this.asks = new HashMap<>();
        this.eventPublisher = new EventPublisher();
        this.eventConsumer = new EventConsumer();
        this.quoteTable = new QuoteTable();
    }

    public int trade(TradeAction tradeAction, String symbol, int quantity, Optional<Float> limitPrice) {
        switch (tradeAction) {
            case BUY: 
                return buy(symbol, quantity, limitPrice);
            case SELL:
            default: 
                return sell(symbol, quantity, limitPrice);
        }
    }

    public int buy(String symbol, int quantity, Optional<Float> limitPrice) {
        validateSymbol(symbol);
        List<Quote> inPlayQuotes = asks.get(symbol).stream()
            .filter(q -> q.getPrice() <= limitPrice.orElse(Float.MAX_VALUE))
            .sorted(Comparator.comparingDouble(Quote::getPrice))
            .toList();

        int remainingQuantity = executeTrades(TradeAction.BUY, quantity, inPlayQuotes);

        if (limitPrice.isPresent() && remainingQuantity > 0) {
            Quote newQuote = new Quote(symbol, limitPrice.get(), remainingQuantity, TradeAction.BUY);
            this.quoteTable.createQuote(newQuote);
            this.bids.get(symbol).add(newQuote);
        }
        return remainingQuantity;
    }

    public int sell(String symbol, int quantity, Optional<Float> limitPrice) {
        validateSymbol(symbol);
        List<Quote> inPlayQuotes = bids.get(symbol).stream()
            .filter(q -> q.getPrice() >= limitPrice.orElse(Float.MIN_VALUE))
            .sorted(Comparator.comparingDouble(Quote::getPrice).reversed())
            .toList();

        int remainingQuantity = executeTrades(TradeAction.SELL, quantity, inPlayQuotes);

        if (limitPrice.isPresent() && remainingQuantity > 0) {
            Quote newQuote = new Quote(symbol, limitPrice.get(), remainingQuantity, TradeAction.SELL);
            this.quoteTable.createQuote(newQuote);
            this.asks.get(symbol).add(newQuote);
        }
        return remainingQuantity;
    }

    private int executeTrades(TradeAction tradeAction, int quantity, List<Quote> inPlayQuotes) {
        int remainingQuantity = quantity;
        Iterator<Quote> iterator = inPlayQuotes.iterator();
        while (remainingQuantity > 0 && iterator.hasNext()) {
            Quote quote = iterator.next();
            int tradedQuantity = 0;
            if (quote.getRemainingQuantity() <= remainingQuantity) {
                if (tradeAction == TradeAction.BUY) {
                    this.asks.get(quote.getSymbol()).remove(quote);
                } else {
                    this.bids.get(quote.getSymbol()).remove(quote);
                }
                tradedQuantity = quote.getRemainingQuantity();
            } else {
                tradedQuantity = remainingQuantity;
            }
            quote.reduceShares(tradedQuantity);
            this.quoteTable.updateQuote(quote);
            eventPublisher.publishTradeEvent(tradeAction, quote, tradedQuantity);
            remainingQuantity -= tradedQuantity;
        }
        return remainingQuantity;
    }

    private void validateSymbol(String symbol) {
        if (!validSymbols.contains(symbol)) {
            throw new IllegalArgumentException(symbol + " is not a valid symbol");
        }

        if (!bids.containsKey(symbol)) {
            bids.put(symbol, new ArrayList<Quote>());
            asks.put(symbol, new ArrayList<Quote>());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String symbol: bids.keySet()) {
            sb.append("******************\n");
            sb.append("****** " + symbol + " ******\n");
            sb.append("******************\n");
            sb.append("======= BID ======\n");
            List<Quote> bidQuotes = bids.get(symbol);
            bidQuotes.sort(Comparator.comparing(Quote::getPrice).reversed());
            for (Quote q: bidQuotes) {
                sb.append(q.toString() + "\n");
            }
            sb.append("======= ASK ======\n");
            List<Quote> askQuotes = asks.get(symbol);
            askQuotes.sort(Comparator.comparing(Quote::getPrice));
            for (Quote q: askQuotes) {
                sb.append(q.toString() + "\n");
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        final Market market = new Market();
        Map<String, Integer> stocks = new HashMap<>();
        stocks.put("AFRM", 60);
        stocks.put("PYPL", 65);

        Random rando = new Random();
        for (int i = 0; i < 100; i++) {
            String symbol = (rando.nextBoolean()) ? "AFRM" : "PYPL";
            TradeAction action = (rando.nextBoolean()) ? TradeAction.SELL : TradeAction.BUY;
            int quantity = rando.nextInt(100);
            Float limit = (rando.nextFloat() * 10) + stocks.get(symbol);
            market.trade(action, symbol, quantity, Optional.of(limit));
        }
        System.out.println(market);
        market.eventConsumer.run();
        
        /*
        market.buy("PYPL", 100, Optional.of(70.00F));
        market.buy("PYPL", 100, Optional.of(71.00F));
        market.buy("PYPL", 100, Optional.of(72.00F));
        market.sell("PYPL", 100, Optional.of(74.00F));
        market.sell("PYPL", 100, Optional.of(75.00F));
        market.sell("PYPL", 100, Optional.of(76.00F));
        System.out.println(market);
        market.sell("PYPL", 201, Optional.empty());
        System.out.println(market);
        market.buy("PYPL", 100, Optional.of(72.00F));
        System.out.println(market);
        market.buy("PYPL", 202, Optional.of(76.00F));
        System.out.println(market);

        market.buy("AFRM", 100, Optional.of(60.00F));
        market.buy("AFRM", 100, Optional.of(61.00F));
        market.buy("AFRM", 100, Optional.of(62.00F));
        market.sell("AFRM", 100, Optional.of(64.00F));
        market.sell("AFRM", 100, Optional.of(65.00F));
        market.sell("AFRM", 100, Optional.of(66.00F));
        System.out.println(market);
        market.sell("AFRM", 201, Optional.empty());
        System.out.println(market);
        market.buy("AFRM", 100, Optional.of(62.00F));
        System.out.println(market);
        market.buy("AFRM", 202, Optional.of(66.00F));
        System.out.println(market);
        */
    }
}
