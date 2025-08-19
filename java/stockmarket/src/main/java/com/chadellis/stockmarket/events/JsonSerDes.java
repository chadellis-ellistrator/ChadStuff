package com.chadellis.stockmarket.events;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerDes implements Serializer<TradeEvent>, Deserializer<TradeEvent> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, TradeEvent data) {
        try {
            return this.objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }    
    }

    @Override
    public TradeEvent deserialize(String topic, byte[] data) {
       try {
            return this.objectMapper.readValue(data, TradeEvent.class);
        } catch (IOException e) {
            System.err.println("Error deserializing: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    
}
