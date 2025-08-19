package com.chadellis.stockmarket.events;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.chadellis.stockmarket.Quote;
import com.chadellis.stockmarket.TradeAction;

public class EventPublisher {
    private KafkaProducer<String, TradeEvent> kafkaProducer;
    private String topicName;

    public EventPublisher() throws IOException {
        final Properties configProperties = new Properties();
        configProperties.load(this.getClass().getResourceAsStream("/config.properties"));

        // 1. Define Kafka broker(s) and topic
        String bootstrapServers = configProperties.getProperty("kafka.broker.address");

        // 2. Configure Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerDes.class.getName()); // Use your custom serializer

        // Optional: Add more configurations like acks, retries, batch.size etc.
        // properties.setProperty(ProducerConfig.ACKS_CONFIG, "all"); // Example: wait for all replicas to acknowledge

        // 3. Create a KafkaProducer instance
        this.kafkaProducer = new KafkaProducer<>(properties);
        this.topicName = configProperties.getProperty("kafka.topicname");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (this.kafkaProducer != null) {
                this.kafkaProducer.close();
                System.out.println("KafkaProducer closed.");
            }
        }));        
    }

    public void publishTradeEvent(TradeAction tradeAction, Quote quote, int quantity) {
        final TradeEvent tradeEvent = new TradeEvent(tradeAction, quote, quantity);
        ProducerRecord<String, TradeEvent> record = new ProducerRecord<>(this.topicName, UUID.randomUUID().toString(), tradeEvent);
        try {
            this.kafkaProducer.send(record);
            System.out.println("Trade event published: " + LocalDateTime.now() + " for "
                + tradeAction.name() + " " + quantity + " " + quote.getSymbol() + " @ " + quote.getPrice());
        } catch(Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }
}