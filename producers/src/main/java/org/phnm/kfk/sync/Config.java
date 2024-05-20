package org.phnm.kfk.sync;

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
        return properties;
    }
}
