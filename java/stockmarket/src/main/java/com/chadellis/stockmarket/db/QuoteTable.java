package com.chadellis.stockmarket.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.chadellis.stockmarket.Quote;
import com.chadellis.stockmarket.TradeAction;

public class QuoteTable {
    public Connection getConnection() throws IOException, SQLException {
        final Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/config.properties"));
        return DriverManager.getConnection(
            properties.getProperty("database.url"),
            properties.getProperty("database.username"),
            properties.getProperty("database.password")
        );
    }

    public List<Quote> loadFromDB() {
        List<Quote> quotes = new ArrayList<Quote>();
        try(Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("select * from Quote where remaining_quantity > 0");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quotes.add(new Quote(
                    rs.getString("symbol"),
                    rs.getFloat("price"),
                    rs.getInt("remaining_quantity"),
                    TradeAction.valueOf(rs.getString("trade_action")),
                    rs.getInt("id")
                ));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return quotes;
    }

    public int createQuote(Quote quote) {
        try(Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "insert into quote (created, updated, symbol, price, initial_quantity, remaining_quantity, trade_action) " +
                    "values (now(),now(),?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, quote.getSymbol());
            stmt.setFloat(2, quote.getPrice());
            stmt.setInt(3, quote.getInitialQuantity());
            stmt.setInt(4, quote.getRemainingQuantity());
            stmt.setString(5, quote.getTradeAction().name());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                quote.setId(rs.getInt(1));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public int updateQuote(Quote quote) {
        try(Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("update quote set updated=now(), remaining_quantity=? where id=?");
            stmt.setInt(1, quote.getRemainingQuantity());
            stmt.setInt(2, quote.getId());
            stmt.execute();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
