package org.phnm.kfk;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

import org.apache.kafka.common.serialization.StringSerializer;

public class Config {
    public static Properties getProducerProperties() {
        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(BATCH_SIZE_CONFIG, "10");
        return properties;
    }

    public static String getSource() {
        return "https://stream.wikimedia.org/v2/stream/recentchange";
    }
}
