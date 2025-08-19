package com.chadellis.stockmarket.events;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


public class EventConsumer {
    private KafkaConsumer<String, TradeEvent> kafkaConsumer;
    private String topicName;
    private boolean running = true;

    public EventConsumer() throws IOException {
        Properties configProperties = new Properties();
        configProperties.load(this.getClass().getResourceAsStream("/config.properties"));
        String bootstrapServers = configProperties.getProperty("kafka.broker.address");
        this.topicName = configProperties.getProperty("kafka.topicname");
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "74561fdc-24c4-4ddc-9380-c70b75dc52d0");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerDes.class.getName());
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // Enable auto-commit for simplicity
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000"); // Commit every 1 second

        // Create the Kafka consumer
        this.kafkaConsumer = new KafkaConsumer<>(properties);

        // Subscribe to a topic
        this.kafkaConsumer.subscribe(Collections.singletonList(this.topicName));
    }

    public void run() {
        try {
            while (this.running) {
                // Poll for new records
                ConsumerRecords<String, TradeEvent> records = this.kafkaConsumer.poll(Duration.ofMillis(100));

                // Process the records
                for (ConsumerRecord<String, TradeEvent> record : records) {
                    System.out.printf("Consumed record from topic %s, partition %d, offset %d: key = %s, value = %s%n",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the consumer when done
            this.kafkaConsumer.close();
            System.out.println("KafkaConsumer closed.");
        }
    }
}
