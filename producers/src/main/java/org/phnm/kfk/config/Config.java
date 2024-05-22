package org.phnm.kfk.config;

import java.util.Properties;
import org.apache.kafka.common.serialization.StringSerializer;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class Config {
    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // producer properties
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(BATCH_SIZE_CONFIG, "10");
        return properties;
    }
}
